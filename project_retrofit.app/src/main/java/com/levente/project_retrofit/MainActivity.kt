package com.levente.project_retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageButton
//import android.widget.Toolbar
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.levente.project_retrofit.api.ThreeTrackerRepository
import com.levente.project_retrofit.databinding.ActivityMainBinding
import com.levente.project_retrofit.viewmodel.SettingsViewModel
import com.levente.project_retrofit.viewmodel.SettingsViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.my_actionbar.view.*
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import java.net.URL

class MainActivity : AppCompatActivity() {

    val TAG: String = javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate() called!")
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView
        val actionBar : Toolbar = findViewById(R.id.my_action_bar)

//        parseXml()

        navView.selectedItemId = R.id.navigation_tasks

//        val appBarConfiguration = AppBarConfiguration(
//            setOf(
//                R.id.navigation_activities,
//                R.id.navigation_tasks,
//                R.id.navigation_groups,
//                R.id.navigation_settings
//            )
//        )
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
//        setupActionBarWithNavController(navController, appBarConfiguration)

        val button = findViewById<ImageButton>(R.id.add_task)

        button.setOnClickListener{
            navController.navigate(R.id.createTaskFragment)
        }

        navView.setupWithNavController(navController)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.loginFragment -> {
                    nav_view.visibility = View.GONE
                    navView.visibility = View.GONE
                    actionBar.visibility=View.GONE
                    actionBar.add_task.visibility=View.GONE
                }
                R.id.splashFragment -> {
                    nav_view.visibility = View.GONE
                    navView.visibility = View.GONE
                    actionBar.visibility=View.GONE
                    actionBar.add_task.visibility=View.GONE
                }
                R.id.listFragment -> {
                    nav_view.visibility = View.VISIBLE
                    navView.visibility = View.VISIBLE
                    actionBar.visibility=View.VISIBLE
                    actionBar.add_task.visibility=View.VISIBLE
                    actionBar.my_title.text=navController.currentDestination?.label
                }
                else -> {
                    nav_view.visibility = View.VISIBLE
                    navView.visibility = View.VISIBLE
                    actionBar.visibility=View.VISIBLE
                    actionBar.add_task.visibility=View.GONE
                    actionBar.my_title.text=navController.currentDestination?.label
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

//    fun parseXml() {
//        val xmlPullParser = XmlPullParserFactory.newInstance().newPullParser()
//        val settingsViewModel : SettingsViewModel
//        val factory = SettingsViewModelFactory(ThreeTrackerRepository())
//        settingsViewModel = ViewModelProvider(this, factory)[SettingsViewModel::class.java]
//        val inputStream = URL(settingsViewModel.setting.value?.image).openConnection().getInputStream()
//        xmlPullParser.setInput(inputStream, null)
//
//        var eventType = xmlPullParser.eventType
//        while (eventType != XmlPullParser.END_DOCUMENT) {
//            if (eventType == XmlPullParser.START_TAG && xmlPullParser.name == "image") {
//                val imageUrl = xmlPullParser.getAttributeValue(null, "src")
//                // You can use the imageUrl variable to display the image in an ImageView or do whatever you need to do with it
//            }
//            eventType = xmlPullParser.next()
//        }
//    }
}