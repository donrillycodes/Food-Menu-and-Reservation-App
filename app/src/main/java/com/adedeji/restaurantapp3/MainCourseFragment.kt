package com.adedeji.restaurantapp3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView

class MainCourseFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_recycler_view, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_menu)

        val mainCourses = arrayOf(
             Dish("Beef and red wine pie", "Tender beef slow-cooked in rich red wine gravy, topped with puff pastry", 2299),
             Dish("Salmon and dill en croute", "Fresh salmon fillet wrapped in buttery pastry with a dill cream sauce", 2499),
             Dish("Eggplant and lentil moussaka", "Layers of roasted eggplant, spiced lentils, and creamy béchamel", 1899),
             Dish("Chicken and wild mushroom risotto", "Creamy Arborio rice with roasted chicken and earthy wild mushrooms", 1999),
             Dish("Herb-crusted rack of lamb", "Tender lamb coated with rosemary and thyme, served with roasted garlic mash", 2599),
             Dish("Butternut squash ravioli", "Handmade pasta filled with roasted squash, served in a sage butter sauce", 1799),
             Dish("Grilled swordfish with salsa verde", "Juicy swordfish steak topped with a zesty herb and caper salsa", 2399)
        )
        val adapter = DishAdapter(mainCourses)
        recyclerView.adapter = adapter

        return view

    }
}