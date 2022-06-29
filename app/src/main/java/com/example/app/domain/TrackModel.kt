package com.example.app.domain

import kotlinx.serialization.Serializable


@Serializable
data class TrackModel(
    var artist: String,
    var image: String,
    var title: String,
    var url: String,
)
