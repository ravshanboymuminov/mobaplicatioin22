package com.example.myapplication


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.assignments.R

class RecipeAdapter(
    private val recipes: List<Recipe>,
    private val itemClickListener: OnItemClickListener
) : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {


    inner class RecipeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val recipeImage: ImageView = view.findViewById(R.id.recipeImage)
        val recipeName: TextView = view.findViewById(R.id.recipeName)
        val recipeDescription: TextView = view.findViewById(R.id.recipeDescription)
        val likeButton: Button = view.findViewById(R.id.likeButton)
        val shareButton: Button = view.findViewById(R.id.shareButton)

        init {
            view.setOnClickListener {
                val recipe = recipes[adapterPosition]
                itemClickListener.onItemClick(recipe.id)
            }
            likeButton.setOnClickListener {
                val recipe = recipes[adapterPosition]
                itemClickListener.onLikeClick(recipe.id)
            }
            shareButton.setOnClickListener {
                val recipe = recipes[adapterPosition]
                itemClickListener.onShareClick(recipe.id)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recipe_item, parent, false)
        return RecipeViewHolder(view)
    }


    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = recipes[position]
        holder.recipeImage.setImageResource(recipe.imageResId)
        holder.recipeName.text = recipe.name
        holder.recipeDescription.text = recipe.description
    }

    override fun getItemCount(): Int = recipes.size

    interface OnItemClickListener {
        fun onItemClick(id: Int)
        fun onLikeClick(id: Int)
        fun onShareClick(id: Int)
    }
}
