package com.example.app.data.room.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "albums")
data class AlbumDBModel(
    val id: Int,
    @PrimaryKey
    val image: String,
    val name: String,
    val trackCount: Int,
    val typeAlbum: String,
)
