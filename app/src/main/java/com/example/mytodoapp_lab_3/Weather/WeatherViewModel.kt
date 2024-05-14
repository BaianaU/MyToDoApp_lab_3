package com.example.mytodoapp_lab_3.weather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.mytodoapp_lab_3.Weather.RetrofitClient
import com.example.mytodoapp_lab_3.Weather.WeatherResponse
import kotlinx.coroutines.Dispatchers
import retrofit2.Response

class WeatherViewModel : ViewModel() {

    fun getWeather(location: String) = liveData(Dispatchers.IO) {
        try {
            val call = RetrofitClient.webservice.getCurrentWeather(location, "557c3d48e70fb777139841164e2ffed9")
            val response: Response<WeatherResponse> = call.execute()
            if (response.isSuccessful && response.body() != null) {
                emit(Result.success(response.body()!!))
            } else {
                emit(Result.failure(RuntimeException("Failed to get weather data")))
            }
        } catch (e: Exception) {
            emit(Result.failure(RuntimeException("Network error", e)))
        }
    }
}
