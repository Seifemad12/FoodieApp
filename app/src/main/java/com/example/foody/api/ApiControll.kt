package com.example.foody.api

import com.example.foody.models.ingredients.IngredientsRoot
import retrofit2.Call
import com.example.foody.models.menuItems.Root
import com.example.foody.models.recipes.FoodRoot
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiControll {

    @GET("food/search?")
    fun getAllFood(@Query("apiKey") key:String):Call<Root>

    @GET("food/menuItems/search?")
    fun getSpecificCategory(
        @Query("query") query:String,
        @Query("apiKey") key:String
    ):Call<Root>

    @GET("recipes/complexSearch?")
    fun getSpecificCategoryRecipe(
        @Query("query") query:String,
        @Query("apiKey") key:String
    ):Call<FoodRoot>


    @GET("recipes/{id}/ingredientWidget.json?")
    fun getIngredientMeal(
        @Path("id") recipe_id:Int,
        @Query("apiKey") key:String
    ):Call<IngredientsRoot>

}