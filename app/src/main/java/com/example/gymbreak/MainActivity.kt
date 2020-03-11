package com.example.gymbreak

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.gymbreak.ui.home.HomeFragment

class MainActivity : AppCompatActivity() {

    private val fragmentManager = supportFragmentManager

    private val navigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                configureHomeFragment()
                return@OnNavigationItemSelectedListener true
            }
            //TODO: - Сделать переход на другой фрагмент
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)

        val appBarConfiguration = AppBarConfiguration(setOf(
                R.id.navigation_home, R.id.navigation_notifications))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        //setSpinnerListener()
    }

    private fun configureHomeFragment() {
        val transaction = fragmentManager.beginTransaction()
        val homeFragment = HomeFragment()
        transaction.replace(R.id.nav_host_fragment, homeFragment)
        transaction.commit()

    }
}