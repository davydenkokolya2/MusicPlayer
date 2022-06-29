package com.example.app.domain

import kotlinx.serialization.Serializable

@Serializable
data class AlbumModel(
    val id: Int,
    val image: String,
    val name: String,
    val trackCount: Int)