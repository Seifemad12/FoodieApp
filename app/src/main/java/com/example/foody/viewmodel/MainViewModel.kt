package com.example.foody.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foody.api.RetrofitInstance
import com.example.foody.models.ingredients.IngredientsRoot
import com.example.foody.models.menuItems.Root
import com.example.foody.models.recipes.FoodRoot
import com.example.foody.repo.Repository
import kotlinx.coroutines.launch
import retrofit2.Call

class MainViewModel(private val repository: Repository):ViewModel() {

    val response:MutableLiveData<Call<Root>> = MutableLiveData()
    val recipe_response:MutableLiveData<Call<FoodRoot>> = MutableLiveData()
    val ingredient_response:MutableLiveData<Call<IngredientsRoot>> = MutableLiveData()

    fun getAllFood(key:String){
        viewModelScope.launch {
            response.value = repository.getAllFood(key)
        }
    }
    fun getSpecificCategory(query:String,key:String){
        viewModelScope.launch {
            response.value = repository.getSpecificCategory(query,key)
        }
    }

    fun getSpecificCategoryRecipe(query:String,key:String){
        viewModelScope.launch {
            recipe_response.value = repository.getSpecificCategoryRecipe(query, key)
        }
    }

    fun getIngredientMeal(recipe_id: Int, key: String) {
        viewModelScope.launch{
            ingredient_response.value = repository.getIngredientMeal(recipe_id, key)
        }
    }
}