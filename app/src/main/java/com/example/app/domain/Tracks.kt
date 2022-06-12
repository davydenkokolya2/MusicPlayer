package com.example.app.domain

import kotlinx.serialization.Serializable


@Serializable
data class Tracks(
    var album: AlbumModel,
    var tracks: ArrayList<TrackModel>
)
