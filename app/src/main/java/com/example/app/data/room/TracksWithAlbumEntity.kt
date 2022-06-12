package com.example.app.data.room

import androidx.room.Embedded
import androidx.room.Relation
import com.example.app.domain.AlbumModel
import com.example.app.domain.TrackModel

data class TracksWithAlbumEntity(
    @Embedded val album: AlbumModel,
    @Relation(
        parentColumn = "id",
        entityColumn = "albumId"
    )
    val tracks: List<TrackModel>
)