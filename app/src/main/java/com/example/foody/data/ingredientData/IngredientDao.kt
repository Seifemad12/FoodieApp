package com.example.foody.data.ingredientData

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.foody.models.databasemodel.SavedIngredients

@Dao
interface IngredientDao {
    @Query("select * from ingredients_table Order By id Asc")
    fun readAllData(): LiveData<List<SavedIngredients>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addIngredient(savedIngredients: SavedIngredients)
}