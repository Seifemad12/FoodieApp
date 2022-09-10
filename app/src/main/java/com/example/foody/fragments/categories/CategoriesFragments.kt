package com.example.foody.fragments.categories

import android.app.ActionBar
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foody.R
import com.example.foody.databinding.FragmentCategoriesFragmentsBinding

class CategoriesFragments : Fragment() {

    lateinit var b:FragmentCategoriesFragmentsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_categories_fragments, container, false)
        b = FragmentCategoriesFragmentsBinding.bind(view)
        val adapter = CategoriesRecycleAdapter()
        b.recylce.adapter = adapter
        b.recylce.layoutManager = LinearLayoutManager(requireContext())
        return view
    }




}