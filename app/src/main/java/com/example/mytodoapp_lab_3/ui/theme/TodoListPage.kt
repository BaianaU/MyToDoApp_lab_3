package com.example.mytodoapp_lab_3.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
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

val Orange = Color(0xFFd37a1c)
val Taupe = Color(0xFF968871)

@Composable
fun TodoListPage(todoViewModel: TodoViewModel, navController: NavController) {
    val todoList by todoViewModel.todoList.observeAsState(emptyList())
    var inputText by remember { mutableStateOf("") }

    Scaffold(
        bottomBar = {
            BottomAppBar(
                containerColor = Taupe // Använd en lämplig färg
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(
                        onClick = { navController.navigate("weather") },
                        colors = ButtonDefaults.buttonColors(containerColor = Taupe),
                    ) {
                        Text(text = "Weather")
                    }
                    Button(
                        onClick = { navController.navigate("calendar") },
                        colors = ButtonDefaults.buttonColors(containerColor = Taupe),
                    ) {
                        Text(text = "Calendar")
                    }
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
                    onValueChange = { inputText = it },
                    label = { Text("Enter Todo") }
                )
                Button(
                    onClick = {
                        todoViewModel.addTodo(inputText)
                        inputText = ""
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Orange),
                    modifier = Modifier.padding(start = 8.dp)
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
