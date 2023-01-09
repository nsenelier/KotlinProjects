package com.example.musictest.model

data class MusicResponse(
    val resultCount: Int,
    val results: List<Music>

)


data class Music(
    val artistName: String,
    val collectionName: String,
    val artworkUrl100: String,
    val trackPrice: Double,
    val previewUrl: String
)