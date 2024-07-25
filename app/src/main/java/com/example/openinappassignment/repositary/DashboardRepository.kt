package com.example.openinappassignment.repositary

import com.example.openinappassignment.model.Dashboard

class DashboardRepository(private val apiService: ApiService) {

    suspend fun getDashboardData(token: String): Dashboard {
        return apiService.getDashboardData("Bearer $token")
    }
}