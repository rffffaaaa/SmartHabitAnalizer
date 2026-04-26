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
import androidx.compose.ui.Alignment
import com.example.smarthabitanalizer.ui.theme.SmartHabitAnalizerTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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

    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

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

        Box(modifier = Modifier.fillMaxSize()) {

            LazyColumn {

                itemsIndexed(habits) { index, habit ->

                    var isLoading by remember { mutableStateOf(false) }

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
                                    if (habit.isDone) "Selesai" else "Belum Selesai"
                                )

                                Switch(
                                    checked = habit.isDone,
                                    onCheckedChange = {}
                                )
                            }

                            Spacer(modifier = Modifier.height(8.dp))

                            Button(
                                onClick = {
                                    coroutineScope.launch {
                                        isLoading = true
                                        delay(2000)

                                        val updatedList = habits.toMutableList()
                                        updatedList[index] =
                                            updatedList[index].copy(isDone = !habit.isDone)
                                        habits = updatedList

                                        snackbarHostState.showSnackbar(
                                            "Habit ${habit.nama} diperbarui"
                                        )

                                        isLoading = false
                                    }
                                },
                                enabled = !isLoading,
                                modifier = Modifier.fillMaxWidth()
                            ) {

                                if (isLoading) {
                                    CircularProgressIndicator(
                                        modifier = Modifier.size(20.dp),
                                        strokeWidth = 2.dp
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text("Memproses...")
                                } else {
                                    Text("Toggle Status")
                                }
                            }
                        }
                    }
                }
            }

            SnackbarHost(
                hostState = snackbarHostState,
                modifier = Modifier.align(Alignment.BottomCenter)
            )
        }
    }
}