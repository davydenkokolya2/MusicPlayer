package com.example.app.data.room.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tracks")
data class TrackDBModel(
    val artist: String,
    val image: String,
    val title: String,
    @PrimaryKey
    val url: String,
    val albumId: Long,
    val typeAlbum: String,
)