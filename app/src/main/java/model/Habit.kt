package com.example.smarthabitanalizer

data class Habit(
    val nama: String,
    val deskripsi: String,
    val durasi: String,
    val imageUrl: String,       // Diubah dari Int (resource) → String (URL) sesuai Modul 12
    val kategori: String = "Umum",
    val isDone: Boolean = false
)