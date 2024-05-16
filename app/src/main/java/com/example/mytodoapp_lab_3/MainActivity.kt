package com.example.mytodoapp_lab_3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mytodoapp_lab_3.ui.theme.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val todoViewModel = ViewModelProvider(this)[TodoViewModel::class.java]
        setContent {
            MyToDoApp_lab_3Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    AppNavHost(navController, todoViewModel)
                }
            }
        }
    }
}

@Composable
fun AppNavHost(navController: NavHostController, todoViewModel: TodoViewModel) {
    NavHost(navController = navController, startDestination = "todoList") {
        composable("todoList") {
            TodoListPage(todoViewModel, navController)
        }
        composable("weather") {
            WeatherPage()
        }
        composable("calendar") {
            CalendarPage()
        }
    }
}
