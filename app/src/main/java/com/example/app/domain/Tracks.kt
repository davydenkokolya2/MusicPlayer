package com.github.krottv.tmstemp.domain

import kotlinx.serialization.Serializable

@Serializable
data class Tracks(
    var artist: String,
    var image: String,
    var title: String,
    var url: String
)
