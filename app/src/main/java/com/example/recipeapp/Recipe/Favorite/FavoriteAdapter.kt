package com.example.recipeapp.Recipe.Favorite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recipeapp.R
import com.example.recipeapp.Recipe.Favorite.FavViewModel.FavoriteViewModel
import com.example.recipeapp.models.FavoriteMeal

class FavoriteAdapter(
    private var values: MutableList<FavoriteMeal>,
    private val viewModel: FavoriteViewModel,
    private val viewLifecycleOwner: LifecycleOwner

) : RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {
    var onItemClick: ((FavoriteMeal) -> Unit)? = null

    init {
        viewModel.FavoritelistAdapter.observe(viewLifecycleOwner) {
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.favourite_row_view, parent, false)
        return ViewHolder(layout)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val meal = values[position]
        holder.text.text = meal.strMeal
        Glide.with(holder.itemView.context).load(meal.strMealThumb).into(holder.image)

        holder.iconFav.setOnClickListener {
            viewModel.deleteFromFavList(meal)
            values.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, values.size)
        }
        holder.itemView.setOnClickListener{
            onItemClick?.invoke(meal)
        }
    }

    override fun getItemCount(): Int = values.size

    class ViewHolder(row: View) : RecyclerView.ViewHolder(row) {
        val image: ImageView = row.findViewById(R.id.SinglFav_Img)
        val iconFav: ImageView = row.findViewById(R.id.SinglFav_FavBttn)
        val text: TextView = row.findViewById(R.id.SinglFav_title)
    }
}
