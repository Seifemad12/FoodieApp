package com.example.foody.fragments.specificCategory;

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.example.foody.R
import com.example.foody.models.menuItems.MenuItem
import com.example.foody.models.recipes.Result


class SpecificCatRecycleAdapter(val context: Context) : RecyclerView.Adapter<SpecificCatRecycleAdapter.SpecificHolder>() {
//    private var root = Root()
    private var specificCategoryList = arrayListOf<MenuItem>()
    private var specificCategoryListRecipe = arrayListOf<Result>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpecificHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.sepecific_category_item, parent, false)
        return SpecificHolder(view)
    }

    override fun getItemCount(): Int {
        return specificCategoryListRecipe.size
    }

    override fun onBindViewHolder(holder: SpecificHolder, position: Int) {
        val item = specificCategoryListRecipe[position]
        holder.item_title.text = item.title
        Glide.with(context).load(item.image).into(holder.food_image)
        holder.itemView.findViewById<ConstraintLayout>(R.id.specificMeal).setOnClickListener {
            val action = SpecificCategoryFragmentDirections.actionSpecificCategoryFragmentToMealFragment(item.id,item.title!!)
            it.findNavController().navigate(action)
        }

    }


    @SuppressLint("NotifyDataSetChanged")
    fun setData(array:ArrayList<MenuItem>){
        this.specificCategoryList = array
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setRecipeData(array:ArrayList<Result>){
        this.specificCategoryListRecipe = array
        notifyDataSetChanged()
    }

    class SpecificHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val item_title = itemView.findViewById<TextView>(R.id.item_title)
        val food_image = itemView.findViewById<ImageView>(R.id.food_image)
    }
}