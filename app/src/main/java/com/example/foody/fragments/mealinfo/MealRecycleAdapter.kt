package com.example.foody.fragments.mealinfo;

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.BitmapFactory
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.foody.R
import com.example.foody.models.ingredients.Ingredient


class MealRecycleAdapter(val context: Context) : RecyclerView.Adapter<MealRecycleAdapter.MealHolder>() {
    private var ingredient_list = arrayListOf<Ingredient>()
    private val src="https://spoonacular.com/cdn/ingredients_100x100/"
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.ingredients_item, parent, false)
        return MealHolder(view)
    }

    override fun getItemCount(): Int {
        return ingredient_list.size
    }

    override fun onBindViewHolder(holder: MealHolder, position: Int) {
        holder.itemView.findViewById<TextView>(R.id.us).text = "US"
        holder.itemView.findViewById<TextView>(R.id.metric).text = "Metric"

        val item = ingredient_list[position]
        holder.item_title.text = item.name
        Glide.with(context).load(src+item.image).error(R.mipmap.ic_foodie).into(holder.ingredient_image)
        val metric = item.amount?.metric?.value.toString()+ " "+ item.amount?.metric?.unit
        holder.metric.text = metric

        val us = item.amount?.us?.value.toString() + " " + item.amount?.us?.unit
        holder.us.text = us

    }

    @SuppressLint("NotifyDataSetChanged")
    fun setIngredientData(arrayList: ArrayList<Ingredient>){
        this.ingredient_list = arrayList
        notifyDataSetChanged()
    }

    class MealHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val item_title = itemView.findViewById<TextView>(R.id.ingredient_title)
        val ingredient_image = itemView.findViewById<ImageView>(R.id.ingredient_image)
        val metric = itemView.findViewById<TextView>(R.id.metricObj)
        val us = itemView.findViewById<TextView>(R.id.usObj)

    }
}