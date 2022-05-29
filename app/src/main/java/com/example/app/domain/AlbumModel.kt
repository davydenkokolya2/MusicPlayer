package com.github.krottv.tmstemp.domain

import android.media.Image
import kotlinx.serialization.Serializable

@Serializable
data class AlbumModel(
    val id: Long,
    val image: String,
    val name: String,
    val trackCount: Int) {

}
