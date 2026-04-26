package com.example.smarthabitanalizer

object HabitSource {

    val dummyHabit = listOf(
        Habit(
            nama = "Olahraga",
            deskripsi = "Lari pagi setiap hari",
            durasi = "30 menit",
            imageRes = R.drawable.olahraga,
        ),
        Habit(
            nama = "Membaca",
            deskripsi = "Membaca buku setiap malam",
            durasi = "20 menit",
            imageRes = R.drawable.membaca,
        ),
        Habit(
            nama = "Tidur",
            deskripsi = "Istirahat yang cukup",
            durasi = "8 jam",
            imageRes = R.drawable.tidur
        )
    )
}