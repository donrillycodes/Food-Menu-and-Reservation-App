package com.adedeji.restaurantapp3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView


class DrinksFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.activity_recycler_view, container, false)

        // Initialize RecyclerView and set adapter
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_menu)

        val drinks = arrayOf(
             Dish("Mango and passionfruit mojito", "A refreshing blend of mango, passionfruit, mint, and rum", 899),
             Dish("Elderflower and cucumber spritz", "Light and floral cocktail with elderflower liqueur and fresh cucumber", 799),
             Dish("Espresso martini", "Vodka, coffee liqueur, and freshly brewed espresso, shaken to perfection", 1199),
             Dish("Berry and mint iced tea", "Cold-brewed black tea infused with mixed berries and fresh mint", 599),
             Dish("Pomegranate gin fizz", "Tart pomegranate juice mixed with gin, soda, and a splash of lime", 849),
             Dish("Spiced chai latte", "Warm, aromatic chai tea blended with steamed milk and a hint of vanilla", 699),
             Dish("Watermelon basil cooler", "Fresh watermelon juice with basil and a touch of vodka, served over ice", 799)
        )
        val adapter = DishAdapter(drinks)
        recyclerView.adapter = adapter

        return view

    }
}