package com.example.app.domain

import kotlinx.serialization.Serializable


@Serializable
data class TracksWithAlbums(
    var album: AlbumModel,
    var tracks: ArrayList<TrackModel>
)
