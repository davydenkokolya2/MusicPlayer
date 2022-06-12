package com.example.app.domain

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity(tableName = "albums")
@Serializable
data class AlbumModel(
    @PrimaryKey
    val id: Long,
    val image: String,
    val name: String,
    val trackCount: Int)