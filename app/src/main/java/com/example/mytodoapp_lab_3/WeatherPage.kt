package com.example.mytodoapp_lab_3.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mytodoapp_lab_3.viewmodel.WeatherViewModel

@Composable
fun WeatherPage() {
    val weatherViewModel: WeatherViewModel = viewModel()
    val weather = weatherViewModel.weather.collectAsState().value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (weather != null) {
            Text(text = "Temperature: ${weather.main.temp}°C")
            Text(text = "Description: ${weather.weather[0].description}")
        } else {
            Text(text = "Loading...")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            weatherViewModel.fetchWeather("Stockholm", "557c3d48e70fb777139841164e2ffed9")
        }) {
            Text(text = "Get Weather")
        }
    }
}
