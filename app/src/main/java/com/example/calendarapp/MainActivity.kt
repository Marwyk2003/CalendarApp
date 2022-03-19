package com.example.calendarapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHost = supportFragmentManager.findFragmentById(R.id.main_fragment) as NavHostFragment
        val appBarConfig = AppBarConfiguration(setOf(R.id.listFragment, R.id.dayFragment))
        val bottomNavView = findViewById<BottomNavigationView>(R.id.main_bottomNav)

        bottomNavView.setupWithNavController(navHost.navController)
        bottomNavView.setOnItemSelectedListener { item ->
            val navController = findNavController(R.id.main_fragment)
            when (item.itemId) {
                R.id.listFragment -> navController.navigate(R.id.listFragment)
                R.id.dayFragment -> navController.navigate(R.id.dayFragment)
            }
            val temp = item.onNavDestinationSelected(navController)
            temp || super.onOptionsItemSelected(item)
        }
        setupActionBarWithNavController(navHost.navController, appBarConfig)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.main_fragment)
        return navController.navigateUp()
    }
}
