package com.example.smarthabitanalizer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.painterResource
import com.example.smarthabitanalizer.ui.theme.SmartHabitAnalizerTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SmartHabitAnalizerTheme {
                SmartHabitUI()
            }
        }
    }
}

@Composable
fun SmartHabitUI() {

    var habits by remember { mutableStateOf(HabitSource.dummyHabit) }
    var newHabit by remember { mutableStateOf("") }
    var showCompletedOnly by remember { mutableStateOf(false) }

    val filteredHabits = if (showCompletedOnly) {
        habits.filter { it.isDone }
    } else {
        habits
    }

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

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = { showCompletedOnly = !showCompletedOnly },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(if (showCompletedOnly) "Tampilkan Semua" else "Hanya Selesai")
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {

            itemsIndexed(filteredHabits) { _, habit ->

                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    ),
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
                                Text(
                                    text = habit.deskripsi,
                                    style = MaterialTheme.typography.bodyMedium
                                )
                                Text(
                                    text = "Durasi: ${habit.durasi}",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.primary
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                if (habit.isDone) "Selesai" else "Belum Selesai",
                                style = MaterialTheme.typography.bodyMedium
                            )

                            Switch(
                                checked = habit.isDone,
                                onCheckedChange = { newValue ->
                                    val updatedList = habits.toMutableList()
                                    val realIndex = habits.indexOf(habit)
                                    updatedList[realIndex] =
                                        updatedList[realIndex].copy(isDone = newValue)
                                    habits = updatedList
                                }
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Button(
                            onClick = {
                                val updatedList = habits.toMutableList()
                                val realIndex = habits.indexOf(habit)
                                updatedList[realIndex] =
                                    updatedList[realIndex].copy(isDone = !habit.isDone)
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
}