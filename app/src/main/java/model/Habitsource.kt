package com.example.smarthabitanalizer

object HabitSource {

    // Gambar kini diambil dari URL internet (Modul 12 - Coil)
    val dummyHabit = listOf(
        Habit(
            nama = "Olahraga",
            deskripsi = "Lari pagi setiap hari untuk menjaga kebugaran tubuh",
            durasi = "30 menit",
            kategori = "Kesehatan",
            imageUrl = "https://images.unsplash.com/photo-1571019613454-1cb2f99b2d8b?w=400"
        ),
        Habit(
            nama = "Membaca",
            deskripsi = "Membaca buku setiap malam sebelum tidur",
            durasi = "20 menit",
            kategori = "Pendidikan",
            imageUrl = "https://images.unsplash.com/photo-1481627834876-b7833e8f5570?w=400"
        ),
        Habit(
            nama = "Tidur Cukup",
            deskripsi = "Istirahat yang cukup untuk pemulihan tubuh",
            durasi = "8 jam",
            kategori = "Kesehatan",
            imageUrl = "https://images.unsplash.com/photo-1541781774459-bb2af2f05b55?w=400"
        ),
        Habit(
            nama = "Meditasi",
            deskripsi = "Melatih fokus dan ketenangan pikiran",
            durasi = "15 menit",
            kategori = "Mental",
            imageUrl = "https://images.unsplash.com/photo-1506126613408-eca07ce68773?w=400"
        ),
        Habit(
            nama = "Minum Air",
            deskripsi = "Memenuhi kebutuhan cairan tubuh setiap hari",
            durasi = "Sepanjang hari",
            kategori = "Kesehatan",
            imageUrl = "https://images.unsplash.com/photo-1559839914-17aae19cec71?w=400"
        )
    )
}