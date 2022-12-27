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
import com.bumptech.glide.Glide
import com.levente.project_retrofit.R
import com.levente.project_retrofit.api.ThreeTrackerRepository
import com.levente.project_retrofit.viewmodel.SettingsViewModel
import com.levente.project_retrofit.viewmodel.SettingsViewModelFactory

class SettingsFragment : Fragment() {

    companion object{
        private val TAG: String = javaClass.simpleName
    }

    private lateinit var settingsViewModel: SettingsViewModel
    private lateinit var logOutButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = SettingsViewModelFactory(ThreeTrackerRepository())

        settingsViewModel = ViewModelProvider(this, factory)[SettingsViewModel::class.java]

    }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("SettingsFragment","OnCreateView called")

        val view = inflater.inflate(R.layout.fragment_settings, container, false)

        val myName : TextView = view.findViewById(R.id.myName)
        val email : TextView = view.findViewById(R.id.myEmailTextView)
        val myJob : TextView = view.findViewById(R.id.myJob)
        val mentorName : TextView = view.findViewById(R.id.mentorName)
        val mentorText : TextView = view.findViewById(R.id.mentorText)
        val phoneNr : TextView = view.findViewById(R.id.phoneNrTextView)
        val location : TextView = view.findViewById(R.id.locationTextView)
        val profilPicture : ImageView = view.findViewById(R.id.myProfileImage)

        settingsViewModel.setting.observe(viewLifecycleOwner){
            Log.d(TAG, "Settings = $it")
            myName.text = settingsViewModel.setting.value?.firstName + " " + settingsViewModel.setting.value?.lastName
            email.text = settingsViewModel.setting.value?.email

            //TODO
            myJob.text = "Szamitastechnika"
            mentorName.text = "Lorinczi Zoltan"
            mentorText.text = "Lorinczi Zoltan mentorlatja"
            phoneNr.text = "0769420911"
            location.text = "Marosvasarhely"
            Glide
                .with(requireContext())
                .load("https://flyinryanhawks.org/wp-content/uploads/2016/08/profile-placeholder.png")
                .override(240,240)
                .fitCenter()
                .into(profilPicture)
        }

        //logOutButton = view.findViewById(R.id.profileLogoutButton)

        //logOutButton.setOnClickListener { findNavController().navigate(R.id.action_profileFragment_to_loginFragment) }

        return view
    }
}