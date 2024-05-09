package com.example.mytodoapp_lab_3.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.mytodoapp_lab_3.Todo
import com.example.mytodoapp_lab_3.getFakeTodo
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun TodoListPage() {
    val todoList = getFakeTodo()

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(8.dp)
    ) {
        LazyColumn {
            itemsIndexed(todoList) { index, item ->
                TodoItem(item) // Use TodoItem composable to display each Todo item
            }
        }
    }
}

@Composable
fun TodoItem(item: Todo) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.primary)
            .padding(16.dp)
    ) {
        Column {
            // Assuming `createdAt` is a field in your Todo data model.
            Text(text = SimpleDateFormat("HH:mm:ss, dd/MM/yyyy", Locale.ENGLISH).format(item.createdAt))
            Text(text = item.title)
        }
    }
}
