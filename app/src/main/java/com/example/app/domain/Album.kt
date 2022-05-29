package com.github.krottv.tmstemp.domain

import kotlinx.serialization.Serializable

@Serializable
data class Album(
    var id: Int,
    var image: String,
    var name: String,
    var trackCount: Int
)
