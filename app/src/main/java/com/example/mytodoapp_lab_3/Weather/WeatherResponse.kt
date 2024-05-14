package com.example.mytodoapp_lab_3.Weather

data class WeatherResponse(
    val main: Main,
    val weather: List<Weather>,
    val name: String
)

data class Main(
    val temp: Double,
    val feels_like: Double
)

data class Weather(
    val description: String,
    val icon: String
)

