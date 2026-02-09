package com.adedeji.restaurantapp3

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.adedeji.restaurantapp3.databinding.ActivityContactBinding
import java.io.Serializable

class ContactActivity : AppCompatActivity() {
    private lateinit var binding: ActivityContactBinding
    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up navigation drawer
        toggle = ActionBarDrawerToggle(this, binding.navDrawerLayout, R.string.open, R.string.close)
        binding.navDrawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Load reservation form by default
//        if (savedInstanceState == null) {
//            showReservationForm()
//        }

        binding.makeReservationButton.setOnClickListener {
            showReservationForm()
            Log.d("ContactActivity", "Make Reservation button clicked")
        }

        binding.navDrawerView.setNavigationItemSelectedListener { navItem ->
            when (navItem.itemId) {
                R.id.nav_drawer_menu -> {
                    startActivity(Intent(this, MenuActivity::class.java).apply {
                        addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
                    })
                    finish()
                    true
                }
                R.id.nav_drawer_profile -> {
                    startActivity(Intent(this, ProfileActivity::class.java))
                    finish()
                    true
                }
                R.id.nav_drawer_contact -> {
                    // Already in ContactActivity
                    true
                }
                else -> false
            }.also {
                binding.navDrawerLayout.closeDrawer(GravityCompat.START)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return toggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        if (binding.navDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.navDrawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    private fun showReservationForm() {
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
            .replace(R.id.contact_fragment_container, ReservationFormFragment())
            .commit()
        Log.d("ContactActivity", "Showing ReservationFormFragment")
    }

    fun showReservationPreview(reservation: Reservation) {
        if (reservation !is Serializable) {
            throw IllegalArgumentException("Reservation must implement Serializable")
        }
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
            .replace(R.id.contact_fragment_container, ReservationPreviewFragment().apply {
                arguments = Bundle().apply {
                    putSerializable("reservation", reservation)
                }
            })
            .addToBackStack(null)
            .commit()
    }
}