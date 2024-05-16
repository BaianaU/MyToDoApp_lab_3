package com.example.mytodoapp_lab_3.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mytodoapp_lab_3.viewmodel.WeatherViewModel

@Composable
fun WeatherPage() {
    val weatherViewModel: WeatherViewModel = viewModel()
    val weather by weatherViewModel.weather.collectAsState()
    var city by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = city,
            onValueChange = { city = it },
            label = { Text("Enter City Name") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
        )

        Button(
            onClick = {
                if (city.isNotBlank()) {
                    weatherViewModel.fetchWeather(city, "557c3d48e70fb777139841164e2ffed9")
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Orange),
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            Text(text = "Get Weather")
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (weather != null) {
            Text(text = "City: $city")
            Text(text = "Temperature: ${weather!!.main.temp}°C")
            Text(text = "Description: ${weather!!.weather[0].description}")
        } else {
            Text(text = "No data available")
        }
    }
}
