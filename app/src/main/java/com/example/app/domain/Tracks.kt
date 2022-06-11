package com.github.krottv.tmstemp.domain

import kotlinx.serialization.Serializable


@Serializable
data class Tracks(
    var album: AlbumModel,
    var tracks: ArrayList<TrackModel>
)
