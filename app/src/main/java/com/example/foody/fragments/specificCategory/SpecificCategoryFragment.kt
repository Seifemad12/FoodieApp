package com.example.foody.fragments.specificCategory

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
import com.example.foody.databinding.FragmentSpecificCategoryBinding
import com.example.foody.models.databasemodel.SavedDataModel
import com.example.foody.models.recipes.FoodRoot
import com.example.foody.models.recipes.Result
import com.example.foody.repo.Repository
import com.example.foody.viewmodel.FoodDatabaseViewModel
import com.example.foody.viewmodel.MainViewModel
import com.example.foody.viewmodel.MainViewModelFactory
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SpecificCategoryFragment : Fragment() {
    lateinit var b: FragmentSpecificCategoryBinding
    private lateinit var viewModel: MainViewModel
    private val args by navArgs<SpecificCategoryFragmentArgs>()
    private lateinit var foodDatabase: FoodDatabaseViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_specific_category, container, false)
        b = FragmentSpecificCategoryBinding.bind(view)

        val adapter = SpecificCatRecycleAdapter(requireContext())
        b.specificRecycle.adapter = adapter
        b.specificRecycle.layoutManager = LinearLayoutManager(requireContext())

        foodDatabase = ViewModelProvider(this)[FoodDatabaseViewModel::class.java]

        if (checkDataFromDatabase()) {
            getFromDatabase(adapter)
            Log.d("database", "Dakhaal fe database")

        } else {
            getFromApi(adapter)
            Log.d("api", "Dakhaal fe api")
        }

        return view
    }

    private fun getFromDatabase(adapter: SpecificCatRecycleAdapter) {
        foodDatabase.readAllData.observe(viewLifecycleOwner, Observer {
            val array = arrayListOf<Result>()
            it.forEach { result->
                if(result.category == args.selectedCategory){
                    array.add(result.result)
                }
            }
            adapter.setRecipeData(array)
        })
    }

    private fun getFromApi(adapter: SpecificCatRecycleAdapter) {
        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
        viewModel.getSpecificCategoryRecipe(args.selectedCategory, getString(R.string.apiKey))

        viewModel.recipe_response.observe(viewLifecycleOwner, Observer {
            it.enqueue(object : Callback<FoodRoot> {
                override fun onResponse(call: Call<FoodRoot>, response: Response<FoodRoot>) {
                    response.body()?.results?.let { it1 -> adapter.setRecipeData(it1) }
                    saveToDatabase()

                    response.body()?.results?.forEach {result->
                        val savedDataModel = SavedDataModel(0,args.selectedCategory,result)
                        foodDatabase.addResult(savedDataModel)
                    }

                }

                override fun onFailure(call: Call<FoodRoot>, t: Throwable) {
                    Toast.makeText(requireContext(), t.message, Toast.LENGTH_SHORT).show()
                }

            })
        })
    }

    private fun saveToDatabase() {
        val sharedPreferences =
            requireActivity().getSharedPreferences("visited", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean(args.selectedCategory, true)
        editor.apply()
    }

    private fun checkDataFromDatabase(): Boolean {
        val sharedPreferences =
            requireActivity().getSharedPreferences("visited", Context.MODE_PRIVATE)

        return sharedPreferences.getBoolean(args.selectedCategory, false)
    }


}