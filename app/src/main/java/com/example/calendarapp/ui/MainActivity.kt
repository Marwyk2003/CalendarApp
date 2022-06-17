package com.example.calendarapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.calendarapp.models.EventData
import com.example.calendarapp.R
import com.example.calendarapp.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val navHost = supportFragmentManager.findFragmentById(R.id.main_fragment) as NavHostFragment
        val appBarConfig = AppBarConfiguration(setOf(R.id.listFragment, R.id.dayFragment))
        val bottomNavView = binding.mainBottomNav
        val navController = navHost.navController

        bottomNavView.setupWithNavController(navController)
        bottomNavView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.listFragment -> navController.navigate(R.id.listFragment)
                R.id.dayFragment -> navController.navigate(R.id.dayFragment)
            }
            val temp = item.onNavDestinationSelected(navController)
            temp || super.onOptionsItemSelected(item)
        }
        setupActionBarWithNavController(navController, appBarConfig)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = binding.mainFragment.findNavController()
        return navController.navigateUp()
    }

    fun navigateToForm(eventData: EventData) {
        val bundle = bundleOf("eventData" to eventData)
        val navController = binding.mainFragment.findNavController()
        navController.navigate(R.id.formFragment, bundle)
    }
}
