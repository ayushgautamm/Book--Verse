package com.example.loginactivityshared.API

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface CrimeRiskApiService {

    @GET("/")
    suspend fun getWelcomeMessage(): Map<String, String>

    @POST("/predict/")
    @Headers("Content-Type: application/json")
    suspend fun predictRisk(@Body request: PredictionRequest): PredictionResponse
}