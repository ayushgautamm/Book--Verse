package com.example.loginactivityshared.API

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CrimeRiskViewModel : ViewModel() {
    private val _welcomeMessage = MutableStateFlow("")
    val welcomeMessage: StateFlow<String> = _welcomeMessage

    private val _predictionResult = MutableStateFlow<PredictionResponse?>(null)
    val predictionResult: StateFlow<PredictionResponse?> = _predictionResult

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    init {
        fetchWelcomeMessage()
    }

    private fun fetchWelcomeMessage() {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.instance.getWelcomeMessage()
                _welcomeMessage.value = (response["message"] ?: "Welcome").toString()
            } catch (e: Exception) {
                _errorMessage.value = "Failed to load welcome message"
            }
        }
    }

    fun predictRisk(city: String, age: Int, gender: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            try {
                val request = PredictionRequest(city, age, gender)
                _predictionResult.value = RetrofitClient.instance.predictRisk(request)
            } catch (e: Exception) {
                _errorMessage.value = "Failed to get prediction: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
}