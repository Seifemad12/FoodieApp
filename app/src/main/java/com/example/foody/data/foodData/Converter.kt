package com.example.foody.data.foodData

import androidx.room.TypeConverter
import com.example.foody.models.recipes.Result
import com.google.gson.Gson

class Converter {
    @TypeConverter
    fun fromGsonTOUser(result:Result):String{
        return Gson().toJson(result)
    }

    @TypeConverter
    fun fromUserToGson(result:String):Result{
        return Gson().fromJson(result,Result::class.java)
    }

    /*@TypeConverter
    fun fromGsonTOUser3(result: Result):String{
        return Gson().toJson(result)
    }

    @TypeConverter
    fun fromUserToGson3(food:String):Result{
        return Gson().fromJson(food,Result::class.java)
    }

    @TypeConverter
    fun fromGsonTOUser2(result:ArrayList<Result>):String{
        return Gson().toJson(result)
    }

    @TypeConverter
    fun fromUserToGson2(food:String):ArrayList<Result>{
        val list = object :TypeToken<ArrayList<Result>>(){}.type
        return Gson().fromJson(food,list)
    }


    @TypeConverter
    fun fromGsonTOUser4(result:LiveData<FoodRoot>):String{
        return Gson().toJson(result)
    }

    @TypeConverter
    fun fromUserToGson4(food:String):LiveData<FoodRoot>{
        val list = object :TypeToken<LiveData<FoodRoot>>(){}.type
        return Gson().fromJson(food,list)
    }*/
}