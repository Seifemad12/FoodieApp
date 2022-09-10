package com.example.foody.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.foody.data.foodData.FoodDao
import com.example.foody.data.foodData.FoodDatabase
import com.example.foody.data.ingredientData.IngredientDao
import com.example.foody.data.ingredientData.IngredientDatabase
import com.example.foody.models.databasemodel.SavedDataModel
import com.example.foody.models.databasemodel.SavedIngredients
import kotlinx.coroutines.launch

class FoodDatabaseViewModel(application: Application):AndroidViewModel(application) {
    var readAllData : LiveData<List<SavedDataModel>>
    var readAllIngredientsData : LiveData<List<SavedIngredients>>

    private var foodDaoObj: FoodDao
    private var ingredientDaoObj: IngredientDao
    init {
        val foodDao = FoodDatabase.getDatabse(application).FoodDao()
        foodDaoObj = foodDao
        readAllData =foodDao.readAllData()


        val ingredDao = IngredientDatabase.getDatabse(application).IngredientDao()
        ingredientDaoObj = ingredDao
        readAllIngredientsData = ingredDao.readAllData()

    }

    fun addResult(result: SavedDataModel){
        viewModelScope.launch {
            foodDaoObj.addResult(result)
        }
    }

    fun addIngredient(savedIngredients: SavedIngredients){
        viewModelScope.launch {
            ingredientDaoObj.addIngredient(savedIngredients)
        }
    }
}