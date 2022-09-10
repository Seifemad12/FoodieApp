package com.example.foody.models.databasemodel

import android.icu.text.CaseMap
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.foody.models.ingredients.Ingredient
import kotlinx.android.parcel.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
@Entity(tableName = "ingredients_table")
data class SavedIngredients(
    @PrimaryKey(autoGenerate = true) var id:Int,
    var mealTitle:String,
    var ingredient: @RawValue Ingredient
):Parcelable