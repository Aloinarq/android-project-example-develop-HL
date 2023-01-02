package com.levente.project_retrofit.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.levente.project_retrofit.App
import com.levente.project_retrofit.api.ThreeTrackerRepository
import com.levente.project_retrofit.api.model.DepartmentResponse
import com.levente.project_retrofit.manager.SharedPreferencesManager
import kotlinx.coroutines.launch

class DepartmentViewModel (private val repository: ThreeTrackerRepository) : ViewModel(){
    companion object{
        private val TAG: String = javaClass.simpleName
    }

    var departments : MutableLiveData<List<DepartmentResponse>> = MutableLiveData()
    var isLoggedIn : MutableLiveData<Boolean> = MutableLiveData()

    init{
        getDepartments()
    }

    private fun getDepartments(){
        Log.d("DepartmentViewModell", "getDepartments() called")

        viewModelScope.launch {
            try {
                val token: String? = App.sharedPreferences.getStringValue(
                    SharedPreferencesManager.KEY_TOKEN,
                    "Empty token!"
                )
                val response = token?.let {
                    repository.getDepartments(it)
                }

                if (response?.isSuccessful == true) {
                    Log.d(TAG, "Get department response: ${response.body()}")
                    val department = response.body()
                    department?.let {
                        isLoggedIn.value = true
                        Log.d("LOGGED", isLoggedIn.value.toString())
                        departments.value = it
                    }
                } else {
                    Log.d(TAG, "Get user error response: ${response?.errorBody()}")
                    isLoggedIn.value = false
                    Log.d("LOGGED", isLoggedIn.value.toString())
                }

            } catch (e: Exception) {
                Log.d(TAG, "DepartmentViewModel - getDepartments() failed with exception: ${e.message}")
                isLoggedIn.value = false
                Log.d("LOGGED", isLoggedIn.value.toString())
            }
        }

    }

    fun getDepartmentName(id: Int): String?{
        val department = departments.value?.firstOrNull{it.ID == id}
        return department?.name
    }
}