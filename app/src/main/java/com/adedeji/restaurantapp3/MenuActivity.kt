package com.adedeji.restaurantapp3

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.adedeji.restaurantapp3.databinding.ActivityMenuBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MenuActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    private lateinit var binding: ActivityMenuBinding
    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupNavigation()

        //default fragment
        setupFragments()
    }

    private fun setupNavigation() {
        // Bottom Navigation
        binding.bottomNavBar.setOnNavigationItemSelectedListener(this)

        // Navigation Drawer
        toggle = ActionBarDrawerToggle(
            this,
            binding.navDrawerLayout,
            R.string.open,
            R.string.close
        ).apply {
            binding.navDrawerLayout.addDrawerListener(this)
            syncState()
        }

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeButtonEnabled(true)
        }

        binding.navDrawerView.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_drawer_menu -> Unit // Already in MenuActivity
                R.id.nav_drawer_profile -> navigateTo(ProfileActivity::class.java)
                R.id.nav_drawer_contact -> navigateTo(ContactActivity::class.java)
            }
            binding.navDrawerLayout.closeDrawer(GravityCompat.START)
            true
        }
    }

    private fun setupFragments() {
        if (supportFragmentManager.findFragmentById(R.id.frame_content) == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.frame_content, MenuCardsFragment())
                .commit()
            supportActionBar?.title = "Restaurant Menu"
        }
    }

    private fun navigateTo(activityClass: Class<*>) {
        if (this::class.java != activityClass) {
            startActivity(Intent(this, activityClass).apply {

                // Prevent stacking MainActivity if already in back stack
                if (activityClass == MainActivity::class.java) {
                    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
                }
            })
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)

//            if (activityClass != MainActivity::class.java) {
//                finish()
//            }
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.navigation_home -> showFragment(MenuCardsFragment(), "Restaurant Menu")
            R.id.navigation_starter -> showFragment(StarterFragment(), "Starters")
            R.id.navigation_main_course -> showFragment(MainCourseFragment(), "Main Course")
            R.id.navigation_dessert -> showFragment(DessertFragment(), "Dessert")
            R.id.navigation_drinks -> showFragment(DrinksFragment(), "Drinks")
            else -> return false
        }
        return true
    }

    private fun showFragment(fragment: androidx.fragment.app.Fragment, title: String) {
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
            .replace(R.id.frame_content, fragment)
            .commit()
        supportActionBar?.title = title
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
}