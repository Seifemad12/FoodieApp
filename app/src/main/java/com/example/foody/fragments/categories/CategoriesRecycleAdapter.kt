package com.example.foody.fragments.categories;

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.foody.R


class CategoriesRecycleAdapter : RecyclerView.Adapter<CategoriesRecycleAdapter.CategoryHolder>() {
    private val categoriesArray = arrayListOf("Beef","Chicken","Fish")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.categories_item, parent, false)
        return CategoryHolder(view)
    }

    override fun getItemCount(): Int {
        return categoriesArray.size
    }

    override fun onBindViewHolder(holder: CategoryHolder, position: Int) {
        holder.category_item.text = categoriesArray[position]
        holder.category_item.setOnClickListener {
            val action = CategoriesFragmentsDirections.actionCategoriesFragmentsToSpecificCategoryFragment(categoriesArray[position])
            holder.itemView.findNavController().navigate(action)
        }
    }

    class CategoryHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val category_item = itemView.findViewById<TextView>(R.id.categoryName)
    }
}