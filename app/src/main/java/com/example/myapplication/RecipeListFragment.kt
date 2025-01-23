// File: RecipeListFragment.kt
package com.example.myapplication.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.assignments.R
import com.example.myapplication.Recipe
import com.example.myapplication.RecipeAdapter
import com.example.myapplication.RecipeDetailActivity

class RecipeListFragment : Fragment(R.layout.fragment_recipe_list), RecipeAdapter.OnItemClickListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RecipeAdapter

    private val recipeList = listOf(
        Recipe(1, "Spaghetti Carbonara", "A delicious Italian pasta dish", R.drawable.spaghetti),
        Recipe(2, "Chicken Alfredo", "A creamy chicken pasta", R.drawable.chicken_alfredo),
        Recipe(3, "Caesar Salad", "A healthy salad with a creamy dressing", R.drawable.caesar_salad)
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_recipe_list, container, false)

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)

        adapter = RecipeAdapter(recipeList, this)
        recyclerView.adapter = adapter

        return view
    }

    // Handle item click event (list item click)
    override fun onItemClick(id: Int) {
        Toast.makeText(activity, "Recipe $id clicked", Toast.LENGTH_SHORT).show()
        // Navigate to Recipe Detail Activity (or Fragment)
        val intent = Intent(activity, RecipeDetailActivity::class.java)
        intent.putExtra("recipeId", id)
        startActivity(intent)
    }

    // Handle like button click
    override fun onLikeClick(id: Int) {
        Toast.makeText(activity, "Liked recipe $id", Toast.LENGTH_SHORT).show()
    }

    // Handle share button click
    override fun onShareClick(id: Int) {
        Toast.makeText(activity, "Shared recipe $id", Toast.LENGTH_SHORT).show()
    }
}
