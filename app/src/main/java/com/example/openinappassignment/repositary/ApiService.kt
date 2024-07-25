package com.example.openinappassignment.repositary

import com.example.openinappassignment.model.Dashboard
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header

interface ApiService {
    @GET("v1/dashboardNew")
    suspend fun getDashboardData(@Header("Authorization") token: String): Dashboard
}

object ApiClient {
    private const val BASE_URL = "https://api.inopenapp.com/api/"

    fun create(): ApiService {
        val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
        return retrofit.create(ApiService::class.java)
    }

}