package com.levente.project_retrofit.fragment

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.levente.project_retrofit.R
import com.levente.project_retrofit.api.ThreeTrackerRepository
import com.levente.project_retrofit.viewmodel.SettingsViewModel
import com.levente.project_retrofit.viewmodel.SettingsViewModelFactory

class SplashFragment : Fragment() {
    private lateinit var settingsViewModel: SettingsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = SettingsViewModelFactory(ThreeTrackerRepository())
        settingsViewModel = ViewModelProvider(this, factory)[SettingsViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        Log.d("SplashFragment", "onCreateView called splash")
        val view = inflater.inflate(R.layout.fragment_splash, container, false)

        settingsViewModel.isLoggedIn.observe(viewLifecycleOwner) {
            if (it == true) {
                Thread.sleep(2000)
                findNavController().navigate(R.id.listFragment)
            } else {
                Thread.sleep(2000)
                findNavController().navigate(R.id.loginFragment)
            }
        }
        return view
    }
}