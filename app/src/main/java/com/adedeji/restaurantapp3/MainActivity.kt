package com.adedeji.restaurantapp3

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import com.adedeji.restaurantapp3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //set up navigation drawer
        toggle = ActionBarDrawerToggle(this, binding.navDrawerLayout, R.string.open, R.string.close)
        binding.navDrawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        // if action bar is not null set home display enabled to true
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.navDrawerView.setNavigationItemSelectedListener {
            navItem ->
            when(navItem.itemId) {
                R.id.nav_drawer_menu -> {
                    val intent = Intent(this, MenuActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.nav_drawer_profile -> {
                    val intent = Intent(this, ProfileActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.nav_drawer_contact -> {
                    val intent = Intent(this, ContactActivity::class.java)
                    startActivity(intent)
                    true
                } else -> false
            }.also {
                binding.navDrawerLayout.close()
            }
        }

    }

    // to make sure the item from the toggle object is returned on selected
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        } else {
            return super.onOptionsItemSelected(item)
        }
    }


}