package com.example.app.data.room.db

import com.example.app.data.room.models.AlbumDBModel
import com.example.app.data.room.models.TrackDBModel

interface AlbumsDbDataSource {
    suspend fun saveAlbums(list: List<AlbumDBModel>)
    suspend fun getItunesAlbums(): List<AlbumDBModel>
    suspend fun getLibraryAlbums(): List<AlbumDBModel>
    suspend fun saveTracks(list: List<TrackDBModel>)
    suspend fun getItunesTracks(albumId: Long): List<TrackDBModel>
    suspend fun getLibraryTracks(albumId: Long): List<TrackDBModel>
}