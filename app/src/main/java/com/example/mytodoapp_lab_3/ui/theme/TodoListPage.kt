package com.example.mytodoapp_lab_3.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mytodoapp_lab_3.Todo
import com.example.mytodoapp_lab_3.getFakeTodo
import java.text.SimpleDateFormat
import java.util.Locale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.Alignment
import com.example.mytodoapp_lab_3.R




@Composable
fun TodoListPage() {
    val todoList = getFakeTodo()
    var inputText by remember { mutableStateOf("")
    }
   

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(8.dp)
    ) {

        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly

        ) {

            OutlinedTextField(value = inputText, onValueChange = {
                inputText = it
            } )

            Button(onClick = { /*TODO*/ },
             colors = ButtonDefaults.buttonColors(containerColor = DarkBlue),
             modifier = Modifier.padding(start = 8.dp)) {
            Text(text = "Add", color = Color.White)
        }
      }

        LazyColumn {

            itemsIndexed(todoList) { index: Int, item: Todo ->
                TodoItem(item)


            }
          }
        }
    }



   val DarkBlue = Color(0xFF00008B)

@Composable
fun TodoItem(item: Todo) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(DarkBlue)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {

            // Assuming `createdAt` is a field in your Todo data model.
            Text(
                text = SimpleDateFormat("HH:mm:ss, dd/MM/yyyy", Locale.ENGLISH).format(item.createdAt),
                fontSize = 10.sp,
                color = Color.LightGray
            )
            Text(
                text = item.title,
                fontSize = 20.sp,
                color = Color.White
            )
        }
        IconButton(onClick = { })   {
              Icon (
                  painter = painterResource(id = R.drawable.baseline_delete_24),
                  contentDescription = "Delete",
                  tint = Color.White

                  )

        }
    }
}