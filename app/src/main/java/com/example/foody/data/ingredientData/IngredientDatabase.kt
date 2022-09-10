package com.example.foody.data.ingredientData

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.foody.data.foodData.FoodDao
import com.example.foody.data.foodData.FoodDatabase
import com.example.foody.models.databasemodel.SavedIngredients

@Database(entities = [SavedIngredients::class], version = 1, exportSchema = false)
@TypeConverters(IngredientConverter::class)
abstract class IngredientDatabase : RoomDatabase() {
    abstract fun IngredientDao(): IngredientDao

    companion object {
        @Volatile
        private var INSTANCE: IngredientDatabase? = null

        fun getDatabse(context: Context): IngredientDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room
                    .databaseBuilder(
                        context.applicationContext,
                        IngredientDatabase::class.java,
                        "ingredients_database"
                    )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}