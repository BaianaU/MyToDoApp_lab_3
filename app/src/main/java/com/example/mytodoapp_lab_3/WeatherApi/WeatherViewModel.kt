package com.example.mytodoapp_lab_3.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mytodoapp_lab_3.api.WeatherApi
import com.example.mytodoapp_lab_3.model.WeatherResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {
    private val weatherApi = WeatherApi.create()
    private val _weather = MutableStateFlow<WeatherResponse?>(null)
    val weather: StateFlow<WeatherResponse?> = _weather

    fun fetchWeather(city: String, apiKey: String) {
        viewModelScope.launch {
            try {
                val response = weatherApi.getWeather(city, apiKey)
                _weather.value = response
            } catch (e: Exception) {
                // Handle error
            }
        }
    }
}
