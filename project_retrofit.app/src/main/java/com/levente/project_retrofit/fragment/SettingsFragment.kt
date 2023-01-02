package com.levente.project_retrofit.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.levente.project_retrofit.App
import com.levente.project_retrofit.R
import com.levente.project_retrofit.api.ThreeTrackerRepository
import com.levente.project_retrofit.manager.SharedPreferencesManager
import com.levente.project_retrofit.viewmodel.DepartmentViewModel
import com.levente.project_retrofit.viewmodel.DepartmentViewModelFactory
import com.levente.project_retrofit.viewmodel.SettingsViewModel
import com.levente.project_retrofit.viewmodel.SettingsViewModelFactory

class SettingsFragment : Fragment() {

    companion object {
        private val TAG: String = javaClass.simpleName
    }

    private lateinit var settingsViewModel: SettingsViewModel
    private lateinit var logOutButton: Button
    private lateinit var updateButton: Button
    private lateinit var departmentViewModel: DepartmentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = SettingsViewModelFactory(ThreeTrackerRepository())
        val factory2 = DepartmentViewModelFactory(ThreeTrackerRepository())

        settingsViewModel = ViewModelProvider(this, factory)[SettingsViewModel::class.java]
        departmentViewModel = ViewModelProvider(this, factory2)[DepartmentViewModel::class.java]

    }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("SettingsFragment", "OnCreateView called")

        val view = inflater.inflate(R.layout.fragment_settings, container, false)

        val myName: TextView = view.findViewById(R.id.myName)
        val email: TextView = view.findViewById(R.id.myEmailTextView)
        val myDepartment: TextView = view.findViewById(R.id.myDepartment)
        val phoneNr: TextView = view.findViewById(R.id.phoneNrTextView)
        val location: TextView = view.findViewById(R.id.locationTextView)
        val profilPicture: ImageView = view.findViewById(R.id.myProfileImage)

        settingsViewModel.setting.observe(viewLifecycleOwner) {
            Log.d(TAG, "Settings = $it")
            myName.text =
                settingsViewModel.setting.value?.firstName + " " + settingsViewModel.setting.value?.lastName
            email.text = settingsViewModel.setting.value?.email

            //TODO
            val myDepartmentId = settingsViewModel.setting.value?.departmentId
            myDepartment.text = myDepartmentId?.let { it1 ->
                departmentViewModel.getDepartmentName(
                    it1
                )
            }
            phoneNr.text = settingsViewModel.setting.value?.phoneNumber
            location.text = settingsViewModel.setting.value?.location

            Glide
                .with(requireContext())
                .load(settingsViewModel.setting.value?.image)
                .override(300, 300)
                .fitCenter()
                .into(profilPicture)
        }

        logOutButton = view.findViewById(R.id.profileLogoutButton)
        logOutButton.setOnClickListener {
            App.sharedPreferences.putStringValue(SharedPreferencesManager.KEY_TOKEN, "")
            findNavController().navigate(R.id.loginFragment)
        }
        updateButton = view.findViewById(R.id.profileUpdateButton)
        updateButton.setOnClickListener {
            findNavController().navigate(R.id.updateProfileFragment)
        }

        return view
    }
}