package com.levente.project_retrofit.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.Factory
import com.levente.project_retrofit.api.ThreeTrackerRepository

class CreateTaskViewModelFactory(private val repository: ThreeTrackerRepository) : Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CreateTaskViewModel(repository) as T
    }
}