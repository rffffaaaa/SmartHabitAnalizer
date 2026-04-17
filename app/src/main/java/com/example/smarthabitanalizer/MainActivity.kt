package com.example.smarthabitanalizer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.painterResource

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SmartHabitUI()
        }
    }
}

@Composable
fun SmartHabitUI() {

    var habits by remember { mutableStateOf(HabitSource.dummyHabit) }
    var newHabit by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        OutlinedTextField(
            value = newHabit,
            onValueChange = { newHabit = it },
            label = { Text("Tambah Habit") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                if (newHabit.isNotBlank()) {
                    habits = habits + Habit(
                        nama = newHabit,
                        deskripsi = "Custom Habit",
                        durasi = "10 menit",
                        imageRes = R.drawable.olahraga,
                        isDone = false
                    )
                    newHabit = ""
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Tambah")
        }

        Spacer(modifier = Modifier.height(16.dp))

        habits.forEachIndexed { index, habit ->

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp)
            ) {

                Column(modifier = Modifier.padding(12.dp)) {

                    Row {

                        Image(
                            painter = painterResource(id = habit.imageRes),
                            contentDescription = habit.nama,
                            modifier = Modifier.size(60.dp)
                        )

                        Spacer(modifier = Modifier.width(12.dp))

                        Column {
                            Text(
                                text = habit.nama,
                                style = MaterialTheme.typography.titleMedium
                            )
                            Text(text = habit.deskripsi)
                            Text(text = "Durasi: ${habit.durasi}")
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(if (habit.isDone) "Selesai" else "Belum Selesai")

                        Switch(
                            checked = habit.isDone,
                            onCheckedChange = { newValue ->
                                val updatedList = habits.toMutableList()
                                updatedList[index] =
                                    updatedList[index].copy(isDone = newValue)
                                habits = updatedList
                            }
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Button(
                        onClick = {
                            val updatedList = habits.toMutableList()
                            updatedList[index] =
                                updatedList[index].copy(isDone = !habit.isDone)
                            habits = updatedList
                        }
                    ) {
                        Text("Toggle Status")
                    }
                }
            }
        }
    }
}