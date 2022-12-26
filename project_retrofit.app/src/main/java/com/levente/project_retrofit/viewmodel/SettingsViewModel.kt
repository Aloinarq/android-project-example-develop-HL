package com.levente.project_retrofit.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.levente.project_retrofit.App
import com.levente.project_retrofit.api.ThreeTrackerRepository
import com.levente.project_retrofit.api.model.SettingsResponse
import com.levente.project_retrofit.manager.SharedPreferencesManager
import kotlinx.coroutines.launch

class SettingsViewModel(private val repository: ThreeTrackerRepository): ViewModel() {

    companion object{
        private val TAG: String = javaClass.simpleName
    }

    var setting : MutableLiveData<SettingsResponse?> = MutableLiveData()

    init{
        getSettings()
    }

    private fun getSettings(){
        Log.d("SettingsViewModell", "getSettings() called")

        viewModelScope.launch {
            try {
                val token: String? = App.sharedPreferences.getStringValue(
                    SharedPreferencesManager.KEY_TOKEN,
                    "Empty token!"
                )
                val response = token?.let {
                    repository.getSettings(it)
                }

                if (response?.isSuccessful == true) {
                    Log.d(TAG, "Get profile response: ${response.body()}")

                    val settings = response.body()
                    settings?.let {
                        setting.value = it
                    }
                } else {
                    Log.d(TAG, "Get user error response: ${response?.errorBody()}")
                }

            } catch (e: Exception) {
                Log.d(TAG, "SettingsViewModel - getSettings() failed with exception: ${e.message}")
            }
        }

    }

}