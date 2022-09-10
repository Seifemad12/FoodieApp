package com.example.foody.repo

import com.example.foody.api.RetrofitInstance
import com.example.foody.models.ingredients.IngredientsRoot
import com.example.foody.models.menuItems.Root
import com.example.foody.models.recipes.FoodRoot
import retrofit2.Call

class Repository {
    suspend fun getAllFood(key: String): Call<Root> {
        return RetrofitInstance.api.getAllFood(key)
    }

    suspend fun getSpecificCategory(query: String, key: String): Call<Root> {
        return RetrofitInstance.api.getSpecificCategory(query, key)
    }

    suspend fun getSpecificCategoryRecipe(query: String, key: String): Call<FoodRoot> {
        return RetrofitInstance.api.getSpecificCategoryRecipe(query, key)
    }

    suspend fun getIngredientMeal(recipe_id: Int, key: String): Call<IngredientsRoot> {
        return RetrofitInstance.api.getIngredientMeal(recipe_id, key)
    }
}