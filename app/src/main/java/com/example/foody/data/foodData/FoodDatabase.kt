package com.example.foody.data.foodData

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.foody.models.databasemodel.SavedDataModel

@Database(entities = [SavedDataModel::class], version = 1, exportSchema = false)
@TypeConverters(Converter::class)
abstract class FoodDatabase: RoomDatabase() {

    abstract fun FoodDao(): FoodDao
    companion object {
        @Volatile
        private var INSTANCE: FoodDatabase? = null

        fun getDatabse(context: Context): FoodDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room
                    .databaseBuilder(
                        context.applicationContext,
                        FoodDatabase::class.java,
                        "food_database"
                    )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}