// File: RecipeDetailActivity.kt
package com.example.myapplication

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.assignments.R

class RecipeDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_detail)


        val recipeId = intent.getIntExtra("recipeId", -1)
        val recipe = getRecipeById(recipeId)


        val imageView: ImageView = findViewById(R.id.recipeDetailImage)
        val nameTextView: TextView = findViewById(R.id.recipeDetailName)
        val descriptionTextView: TextView = findViewById(R.id.recipeDetailDescription)

        // Set the recipe image using the image resource ID
        imageView.setImageResource(recipe.imageResId)
        nameTextView.text = recipe.name
        descriptionTextView.text = recipe.description
    }

    private fun getRecipeById(id: Int): Recipe {
        // Return a dummy recipe for now, based on the ID
        return Recipe(id, "Recipe Name", "Recipe Description", R.drawable.spaghetti)
    }
}
