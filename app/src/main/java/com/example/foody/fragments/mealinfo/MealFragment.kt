package com.example.foody.fragments.mealinfo

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foody.R
import com.example.foody.databinding.FragmentMealBinding
import com.example.foody.fragments.specificCategory.SpecificCategoryFragmentArgs
import com.example.foody.models.databasemodel.SavedDataModel
import com.example.foody.models.databasemodel.SavedIngredients
import com.example.foody.models.ingredients.Ingredient
import com.example.foody.models.ingredients.IngredientsRoot
import com.example.foody.models.recipes.Result
import com.example.foody.repo.Repository
import com.example.foody.viewmodel.FoodDatabaseViewModel
import com.example.foody.viewmodel.MainViewModel
import com.example.foody.viewmodel.MainViewModelFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MealFragment : Fragment() {
    private lateinit var b: FragmentMealBinding
    private lateinit var viewModel: MainViewModel
    private val args by navArgs<MealFragmentArgs>()
    private lateinit var foodDatabase: FoodDatabaseViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_meal, container, false)
        b = FragmentMealBinding.bind(view)

        val adapter = MealRecycleAdapter(requireContext())
        b.ingredRecycle.adapter = adapter
        b.ingredRecycle.layoutManager = LinearLayoutManager(requireContext())

        foodDatabase = ViewModelProvider(this)[FoodDatabaseViewModel::class.java]

        if (checkDataFromDatabase()) {
            getFromDatabase(adapter)
            Log.d("database2", "Dakhaal fe database")

        } else {
            getFromApi(adapter)
            Log.d("api2", "Dakhaal fe api")
        }


        return view
    }

    private fun getFromDatabase(adapter: MealRecycleAdapter) {
        foodDatabase.readAllIngredientsData.observe(viewLifecycleOwner, Observer {
            val array = arrayListOf<Ingredient>()
            it.forEach { savedIngredients ->
                if (savedIngredients.mealTitle == args.mealTitle) {
                    array.add(savedIngredients.ingredient)
                }
            }
            adapter.setIngredientData(array)
        })
    }

    private fun getFromApi(adapter: MealRecycleAdapter) {
        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
        viewModel.getIngredientMeal(args.mealID, getString(R.string.apiKey))

        viewModel.ingredient_response.observe(viewLifecycleOwner, Observer {
            it.enqueue(object : Callback<IngredientsRoot> {
                override fun onResponse(
                    call: Call<IngredientsRoot>,
                    response: Response<IngredientsRoot>
                ) {
                    response.body()?.ingredients?.let { it1 -> adapter.setIngredientData(it1) }
                    saveToDatabase()

                    response.body()?.ingredients?.forEach { result ->
                        val savedIngredients = SavedIngredients(0, args.mealTitle, result)
                        foodDatabase.addIngredient(savedIngredients)
                    }
                }

                override fun onFailure(call: Call<IngredientsRoot>, t: Throwable) {
                    Toast.makeText(requireContext(), t.message, Toast.LENGTH_SHORT).show()
                }

            })
        })
    }

    private fun saveToDatabase() {
        val sharedPreferences =
            requireActivity().getSharedPreferences("ingredient", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean(args.mealTitle, true)
        editor.apply()
    }

    private fun checkDataFromDatabase(): Boolean {
        val sharedPreferences =
            requireActivity().getSharedPreferences("ingredient", Context.MODE_PRIVATE)

        return sharedPreferences.getBoolean(args.mealTitle, false)
    }
}