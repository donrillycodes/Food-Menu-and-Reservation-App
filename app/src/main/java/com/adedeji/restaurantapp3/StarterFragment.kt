package com.adedeji.restaurantapp3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView

class StarterFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.activity_recycler_view, container, false)

        // Initialize RecyclerView and set adapter
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_menu)

        val starters = arrayOf(
            Dish("Bruschetta with tomato and basil", "Toasted bread topped with fresh tomatoes, basil, and garlic", 1099),
            Dish("Truffle and mushroom arancini", "Crispy risotto balls filled with truffle oil and wild mushrooms", 1399),
            Dish("Prawn and avocado cocktail", "Succulent prawns layered with creamy avocado and tangy cocktail sauce", 1599),
            Dish("Spinach and feta filo parcels", "Flaky filo pastry stuffed with spinach and feta cheese", 1299),
            Dish("Caprese stuffed mushrooms", "Portobello mushrooms filled with mozzarella, cherry tomatoes, and basil pesto", 1199),
            Dish("Spicy chorizo croquettes", "Crispy croquettes with smoky chorizo and a manchego cheese center", 1349),
            Dish("Smoked salmon tartare", "Finely chopped smoked salmon with capers, red onion, and lemon zest on toast", 1499)
        )

        val adapter = DishAdapter(starters)
        recyclerView.adapter = adapter

        return view

    }

}