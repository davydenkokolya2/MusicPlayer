package com.github.krottv.tmstemp.domain

import kotlinx.serialization.Serializable

@Serializable
data class TracksModel(
    var album: Album ,
    var tracks: ArrayList<Tracks>
)
