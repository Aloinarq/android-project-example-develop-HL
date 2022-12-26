package com.levente.project_retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.levente.project_retrofit.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val TAG: String = javaClass.simpleName



    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate() called!")
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView
        //val logoAnimation = findViewById<View>(R.id.logo_animation) as AnimationDrawable
        //logoAnimation.start()

        navView.selectedItemId = R.id.navigation_activities

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_activities,
                R.id.navigation_tasks,
                R.id.navigation_groups,
                R.id.navigation_settings
            )
        )
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        setupActionBarWithNavController(navController, appBarConfiguration)

        navView.setupWithNavController(navController)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.loginFragment -> {
                    nav_view.visibility = View.GONE
                    navView.visibility = View.GONE
                }
                else -> {
                    nav_view.visibility = View.VISIBLE
                    navView.visibility = View.VISIBLE
                }
            }
        }

        navView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.navigation_activities -> {
                    navController.navigate(R.id.activitiesFragment)
                }
                R.id.navigation_tasks -> {
                    navController.navigate(R.id.listFragment)
                }
                R.id.navigation_groups -> {
                    navController.navigate(R.id.groupsFragment)
                }
                R.id.navigation_settings -> {
                    navController.navigate(R.id.settingsFragment)
                }
            }
            true
        }
    }
}