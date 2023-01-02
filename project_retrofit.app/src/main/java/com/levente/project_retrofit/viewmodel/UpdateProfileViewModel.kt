package com.levente.project_retrofit.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.levente.project_retrofit.App
import com.levente.project_retrofit.api.ThreeTrackerRepository
import com.levente.project_retrofit.api.model.UpdateProfileRequestBody
import com.levente.project_retrofit.manager.SharedPreferencesManager
import kotlinx.coroutines.launch

class UpdateProfileViewModel(private val repository: ThreeTrackerRepository) : ViewModel() {
    companion object {
        private val TAG: String = javaClass.simpleName
    }

    var isSuccessful: MutableLiveData<Boolean> = MutableLiveData()

    fun updateProfile(
        lastName: String,
        firstName: String,
        location: String,
        phoneNumber: String,
        imageUrl: String
    ) {
        val requestBody =
            UpdateProfileRequestBody(lastName, firstName, location, phoneNumber, imageUrl)
        viewModelScope.launch { executeUpdateProfile(requestBody) }
    }

    private suspend fun executeUpdateProfile(requestBody: UpdateProfileRequestBody) {
        try {
            val token: String? = App.sharedPreferences.getStringValue(
                SharedPreferencesManager.KEY_TOKEN,
                "Empty token"
            )
            val response = token?.let {
                repository.updateProfile(requestBody, token)
            }

            if (response != null) {
                if (response.isSuccessful) {
                    Log.d(TAG, "UpdateProfile response: ${response.body()}")
                    isSuccessful.value = true
                } else {
                    Log.d(TAG, "UpdateProfile error response: ${response.message()}")
                    isSuccessful.value = false
                }
            }
        } catch (e: Exception) {
            Log.d(
                TAG,
                "UpdateProfileViewModel - updateProfile() failed with exception: ${e.message}"
            )
            isSuccessful.value = false
        }
    }
}