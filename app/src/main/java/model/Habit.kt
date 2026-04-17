package com.example.smarthabitanalizer

data class Habit(
    val nama: String,
    val deskripsi: String,
    val durasi: String,
    val imageRes: Int,
    val isDone: Boolean = false
)