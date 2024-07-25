package com.example.openinappassignment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.openinappassignment.model.Dashboard
import com.example.openinappassignment.repositary.DashboardRepository
import kotlinx.coroutines.launch

class DashboardViewModel(private val repository: DashboardRepository) : ViewModel() {

    private val _dashboardData = MutableLiveData<Dashboard>()
    val dashboardData: LiveData<Dashboard> get() = _dashboardData

    fun fetchDashboardData(token: String) {
        viewModelScope.launch {
            val response = repository.getDashboardData(token)
            _dashboardData.postValue(response)
        }
    }
}

class DashboardViewModelFactory(private val repository: DashboardRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DashboardViewModel::class.java)) {
            return DashboardViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}