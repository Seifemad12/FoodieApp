package com.example.foody.data.foodData

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.foody.models.databasemodel.SavedDataModel
import com.example.foody.models.recipes.FoodRoot
import com.example.foody.models.recipes.Result

@Dao
interface FoodDao {
    @Query("select * from categories_table Order By id Asc")
    fun readAllData():LiveData<List<SavedDataModel>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addResult(savedDataModel: SavedDataModel)
}