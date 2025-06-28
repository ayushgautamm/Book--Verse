

package com.example.loginactivityshared.API


data class PredictionRequest(
    val city: String,
    val age: Int,
    val gender: Int
)

     data class PredictionResponse(
    val predicted_risk_level: String,
    val details: PredictionDetails
    )

   data class PredictionDetails(
    val City: String,
    val Age: Int,
    val Gender: Int,
    val Hour: Int,
    val Month: Int
)
