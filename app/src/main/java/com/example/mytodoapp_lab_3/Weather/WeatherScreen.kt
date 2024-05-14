import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mytodoapp_lab_3.weather.WeatherViewModel
import kotlin.Result


@Composable
fun WeatherScreen(
    weatherViewModel: WeatherViewModel = viewModel(),
    paddingValues: PaddingValues = PaddingValues()
) {
    val weatherState = weatherViewModel.getWeather("Stockholm").collectAsState(initial = Result.failure(RuntimeException("Loading..."))).value

    Surface(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
        Column(modifier = Modifier.padding(16.dp)) {
            when (weatherState) {
                is Result.Success -> {
                    val weatherData = weatherState.getOrNull()
                    if (weatherData != null) {
                        Text(
                            text = "Temperature in ${weatherData.name}: ${weatherData.main.temp}°C",
                            style = MaterialTheme.typography.titleLarge
                        )
                        Text(
                            text = "Feels like: ${weatherData.main.feels_like}°C",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(
                            text = "Condition: ${weatherData.weather.firstOrNull()?.description.orEmpty()}",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
                is Result.Failure -> {
                    Text(
                        text = "Failed to load weather data: ${weatherState.exceptionOrNull()?.message}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                else -> {
                    Text(
                        text = "Loading weather data...",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewWeatherScreen() {
    WeatherScreen()
}
