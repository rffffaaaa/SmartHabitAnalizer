package com.example.smarthabitanalizer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.smarthabitanalizer.ui.theme.SmartHabitAnalizerTheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SmartHabitAnalizerTheme {
                HabitScreen()
            }
        }
    }
}

@Composable
fun HabitScreen() {

    val habits = HabitSource.dummyHabit

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        habits.forEach { habit ->

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {

                Row(
                    modifier = Modifier.padding(12.dp)
                ) {

                    Image(
                        painter = painterResource(id = habit.imageRes),
                        contentDescription = habit.nama,
                        modifier = Modifier.size(80.dp)
                    )

                    Spacer(modifier = Modifier.width(12.dp))

                    Column {
                        Text(text = habit.nama)
                        Text(text = habit.deskripsi)
                        Text(text = "Durasi: ${habit.durasi}")
                    }
                }
            }
        }
    }
}