package com.example.mytodoapp_lab_3.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mytodoapp_lab_3.R
import com.example.mytodoapp_lab_3.Todo
import com.example.mytodoapp_lab_3.TodoViewModel
import java.text.SimpleDateFormat
import java.util.Locale
import androidx.compose.runtime.livedata.observeAsState

val Taupe = Color(0xFF968871)
val Orange = Color(0xFFd37a1c)
val DarkBlue = Color(0xFF00008B)  // Dark blue color

@Composable
fun TodoListPage(todoViewModel: TodoViewModel, navController: NavController) {
    val todoList by todoViewModel.todoList.observeAsState(emptyList())
    var inputText by remember { mutableStateOf("") }

    Scaffold(
        bottomBar = {
            BottomAppBar(
                containerColor = Taupe  // Korrigerad parameter
            ) {
                Button(
                    onClick = {
                        navController.navigate("weather")
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Taupe),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Text(text = "Weather today", color = Color.White)
                }
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(padding)
                .padding(8.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                OutlinedTextField(
                    modifier = Modifier.weight(1f),
                    value = inputText,
                    onValueChange = { inputText = it }
                )
                Button(
                    onClick = {
                        todoViewModel.addTodo(inputText)
                        inputText = ""
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Orange),  // Set the button color to dark blue
                    modifier = Modifier.padding(start = 8.dp)  // Space between text field and button
                ) {
                    Text(text = "Add", color = Color.White)
                }
            }

            if (todoList.isEmpty()) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    text = "No items yet",
                    fontSize = 16.sp
                )
            } else {
                LazyColumn(
                    modifier = Modifier.weight(1f),
                    content = {
                        itemsIndexed(todoList) { _, item ->
                            TodoItem(item = item, onDelete = { todoViewModel.deleteTodo(item.id) })
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun TodoItem(item: Todo, onDelete: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Taupe)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = SimpleDateFormat("HH:mm:aa, dd/MM/yyyy", Locale.ENGLISH).format(item.createdAt),
                fontSize = 12.sp,
                color = Color.LightGray
            )
            Text(
                text = item.title,
                fontSize = 20.sp,
                color = Color.White
            )
        }
        IconButton(onClick = onDelete) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_delete_24),
                contentDescription = "Delete",
                tint = Color.White
            )
        }
    }
}
