package com.example.app.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.app.data.room.models.TrackDBModel
import kotlinx.coroutines.flow.Flow

@Dao
interface TracksDao {

    @Query("select * from tracks where albumId = :albumId and typeAlbum = 'library'")
    fun getLibraryTracks(albumId: Long): List<TrackDBModel>

    @Query("select * from tracks where albumId = :albumId and typeAlbum = 'itunes'")
    fun getItunesTracks(albumId: Long): List<TrackDBModel>

    @Insert
    fun saveTracks(list: List<TrackDBModel>)
}