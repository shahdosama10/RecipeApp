package com.example.recipeapp.Recipe.Home

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.recipeapp.R
import com.example.recipeapp.models.CategoriesResponse
import com.example.recipeapp.models.Category

class CategoryListAdapetr(private val categories: CategoriesResponse): RecyclerView.Adapter<CategoryListAdapetr.CategoryListViewHolder>() {

    var onItemClick: ((Category) -> Unit)? = null

    class CategoryListViewHolder(private val view: View): RecyclerView.ViewHolder(view){
        private var categoryImage : ImageView? = null
        private var categoryTitle: TextView? = null

        fun getCategoryImage(): ImageView {
            return categoryImage ?: view.findViewById(R.id.categoryImage)
        }

        fun getCategoryTitle(): TextView {
            return categoryTitle ?: view.findViewById(R.id.categoryTitle)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryListViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.category_list_item, parent, false)
        return CategoryListViewHolder(layout)
    }

    override fun getItemCount(): Int {
        return categories.categories.size?:0
    }

    override fun onBindViewHolder(holder: CategoryListViewHolder, position: Int) {
        holder.getCategoryTitle().text = categories.categories[position].strCategory
        Glide.with(holder.itemView.context).load(categories.categories[position].strCategoryThumb).apply(
            RequestOptions().placeholder(R.drawable.baseline_access_time_24)
                .error(R.drawable.baseline_assignment_late_24)
        ).into(holder.getCategoryImage())

        holder.itemView.setOnClickListener {    // get the item clicked on
            onItemClick?.invoke(categories.categories[position])
        }

    }
}