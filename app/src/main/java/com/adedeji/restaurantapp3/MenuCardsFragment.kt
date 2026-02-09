package com.adedeji.restaurantapp3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.adedeji.restaurantapp3.databinding.FragmentMenuCardsBinding

class MenuCardsFragment : Fragment() {
    private lateinit var binding: FragmentMenuCardsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMenuCardsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.cardViewStarters.setOnClickListener {
            navigateToFragment(StarterFragment())
        }
        binding.cardViewMainCourse.setOnClickListener {
            navigateToFragment(MainCourseFragment())
        }
        binding.cardViewDessert.setOnClickListener {
            navigateToFragment(DessertFragment())
        }
        binding.cardViewDrinks.setOnClickListener {
            navigateToFragment(DrinksFragment())
        }


    }

    private fun navigateToFragment(fragment: Fragment) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.frame_content, fragment)
            .commit()
    }

}