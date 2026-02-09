package com.adedeji.restaurantapp3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView

class DessertFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_recycler_view, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_menu)

        val desserts = arrayOf(
             Dish("Chocolate and hazelnut fondant", "Warm molten chocolate cake with a gooey hazelnut center", 1499),
             Dish("Sticky toffee pudding", "Moist date sponge soaked in toffee sauce, served with vanilla ice cream", 1299),
             Dish("Lemon and raspberry tart", "Zesty lemon curd and fresh raspberries in a buttery shortcrust pastry", 1399),
             Dish("Tiramisu with espresso", "Classic Italian dessert with layers of coffee-soaked ladyfingers and mascarpone", 1599),
             Dish("Salted caramel cheesecake", "Creamy cheesecake with a rich salted caramel swirl and biscuit base", 1399),
             Dish("Pistachio and rose panna cotta", "Silky panna cotta infused with rosewater and topped with crushed pistachios", 1299),
             Dish("Apple and cinnamon crumble", "Warm spiced apples topped with a buttery oat crumble, served with custard", 1349)
        )

        val adapter = DishAdapter(desserts)
        recyclerView.adapter = adapter

        return view
    }
}