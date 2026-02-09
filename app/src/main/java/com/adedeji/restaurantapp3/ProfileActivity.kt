package com.adedeji.restaurantapp3

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.adedeji.restaurantapp3.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding
    private lateinit var toggle: ActionBarDrawerToggle
    private var reservation: Reservation? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set user details
        binding.profileFullName.text = "Emmanuel Oyenuga"
        binding.studentId.text = "3193515"

        // Handle new reservation from intent
        handleIntent(intent)
        Log.d("ProfileActivity", "Received intent with reservation: ${intent?.getSerializableExtra("Reservation")}")

        // Set up navigation drawer
        toggle = ActionBarDrawerToggle(this, binding.navDrawerLayout, R.string.open, R.string.close)
        binding.navDrawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

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
                    // Already in ProfileActivity
                    true
                }
                R.id.nav_drawer_contact -> {
                    startActivity(Intent(this, ContactActivity::class.java))
                    finish()
                    true
                }
                else -> false
            }.also {
                binding.navDrawerLayout.closeDrawer(GravityCompat.START)
            }
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
        handleIntent(intent)
        Log.d("ProfileActivity", "Received intent with reservation: ${intent?.getSerializableExtra("Reservation")}")
    }

    private fun handleIntent(intent: Intent?) {
        intent?.getSerializableExtra("Reservation")?.let {
            reservation = it as Reservation
            updateReservationCard()
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

    private fun updateReservationCard() {
        reservation?.let {
            binding.reservationCard.visibility = View.VISIBLE
            binding.noReservations.visibility = View.GONE
            binding.reservationName.text = it.name
            binding.reservationDate.text = "Date: ${it.date}"
            binding.reservationGuests.text = "Guests: ${it.guests}"
            binding.reservationPhone.text = "Phone: ${it.phoneNumber}"
        }
    }
}