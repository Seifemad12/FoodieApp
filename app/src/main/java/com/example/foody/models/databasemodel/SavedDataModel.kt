package com.example.foody.models.databasemodel

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.foody.models.recipes.FoodRoot
import com.example.foody.models.recipes.Result
import kotlinx.android.parcel.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
@Entity(tableName = "categories_table")
data class SavedDataModel(
    @PrimaryKey(autoGenerate = true) val id: Int,var category: String,
    var result: @RawValue Result
    ):Parcelable