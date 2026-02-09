package com.adedeji.restaurantapp3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DishAdapter(private val dishes: Array<Dish>): RecyclerView.Adapter<DishAdapter.DishViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DishViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_dish, parent, false)

        return DishViewHolder(view)
    }
    override fun onBindViewHolder(holder: DishViewHolder, position: Int) {
        val dish = dishes[position]
        holder.bind(dish)
    }
    override fun getItemCount(): Int = dishes.size

    class DishViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.title_text)
        private val descriptionTextView: TextView = itemView.findViewById(R.id.description_text)
        private val priceTextView: TextView = itemView.findViewById(R.id.price_text)

        fun bind(dish: Dish) {
            titleTextView.text = dish.title
            descriptionTextView.text = dish.description
            priceTextView.text = "$${"%.2f".format(dish.price / 100.0)}"
        }
    }
}