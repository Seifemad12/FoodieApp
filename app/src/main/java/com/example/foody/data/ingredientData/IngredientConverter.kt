package com.example.foody.data.ingredientData

import androidx.room.TypeConverter
import com.example.foody.models.ingredients.Amount
import com.example.foody.models.ingredients.Ingredient
import com.example.foody.models.ingredients.Us

import com.google.gson.Gson

class IngredientConverter {
    @TypeConverter
    fun fromGsonTOUser(result: Ingredient):String{
        return Gson().toJson(result)
    }

    @TypeConverter
    fun fromUserToGson(result:String):  Ingredient{
        return Gson().fromJson(result, Ingredient::class.java)
    }

    @TypeConverter
    fun fromGsonTOUser2(result: Amount):String{
        return Gson().toJson(result)
    }

    @TypeConverter
    fun fromUserToGson2(result:String):  Amount{
        return Gson().fromJson(result, Amount::class.java)
    }

    @TypeConverter
    fun fromGsonTOUser3(result: Us):String{
        return Gson().toJson(result)
    }

    @TypeConverter
    fun fromUserToGson3(result:String):  Us{
        return Gson().fromJson(result, Us::class.java)
    }
}