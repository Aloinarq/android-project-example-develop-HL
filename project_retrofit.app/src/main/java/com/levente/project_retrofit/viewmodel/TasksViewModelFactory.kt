package com.levente.project_retrofit.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.Factory
import com.levente.project_retrofit.api.ThreeTrackerRepository

class TasksViewModelFactory(private val repository: ThreeTrackerRepository) : Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TasksViewModel(repository) as T
    }
}