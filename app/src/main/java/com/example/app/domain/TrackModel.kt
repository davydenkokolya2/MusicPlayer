package com.example.app.domain

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity(tableName = "tracks")
@Serializable
data class TrackModel(
    @PrimaryKey val id: Long,
    var artist: String,
    var image: String,
    var title: String,
    var url: String,
   // val albumId: Long
)
