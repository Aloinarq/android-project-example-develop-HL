package com.levente.project_retrofit.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.levente.project_retrofit.App
import com.levente.project_retrofit.api.model.SettingsResponse
import androidx.lifecycle.viewModelScope
import com.levente.project_retrofit.api.ThreeTrackerRepository
import com.levente.project_retrofit.manager.SharedPreferencesManager
import kotlinx.coroutines.launch

class UsersViewModel (private val repository: ThreeTrackerRepository): ViewModel(){
    companion object{
        private val TAG: String = javaClass.simpleName
    }

    val users: MutableLiveData<List<SettingsResponse>> = MutableLiveData()


    init{
        getUsers()
    }

    private fun getUsers(){
        viewModelScope.launch {
            try {
                val token: String? = App.sharedPreferences.getStringValue(
                    SharedPreferencesManager.KEY_TOKEN,
                    "Empty token!"
                )
                val response = token?.let {
                    repository.getUsers(it)
                }

                if (response?.isSuccessful == true) {
                    Log.d(TAG, "Get users response: ${response.body()}")

                    val userList = response.body()
                    userList?.let {
                        users.value = userList
                    }
                } else {
                    Log.d(TAG, "Get users error response: ${response?.errorBody()}")
                }

            } catch (e: Exception) {
                Log.d(TAG, "UsersViewModel - getUsers() failed with exception: ${e.message}")
            }
        }
    }

    fun getUsersByDepartmentID(id: Int): List<SettingsResponse?>? {
        getUsers()
        return users.value?.filter { it.departmentId  == id  }
    }

    fun getUserNameByID(id: Int): String {
        getUsers()
        val user = users.value?.singleOrNull{it.id == id}

        return (user?.firstName ?:"") + " " + (user?.lastName ?:"")
    }
}
