package com.example.app.data.room.db

import com.example.app.data.room.MyDatabase
import com.example.app.data.room.models.AlbumDBModel
import com.example.app.data.room.models.TrackDBModel

class AlbumsRoomDataSource(database: MyDatabase) : AlbumsDbDataSource {

    private val daoAlbums = database.provideDao()
    private val daoTracks = database.provideDaoTracks()

    override suspend fun saveAlbums(list: List<AlbumDBModel>) {
        daoAlbums.saveAlbums(list)
    }

    override suspend fun getItunesAlbums(): List<AlbumDBModel> {
        return daoAlbums.getItunesAlbums().map { it.copy(name = it.name + "_itunes") }

    }

    override suspend fun getLibraryAlbums(): List<AlbumDBModel> {
        return daoAlbums.getLibraryAlbums().map { it.copy(name = it.name + "_library") }
    }

    override suspend fun saveTracks(list: List<TrackDBModel>) {
        daoTracks.saveTracks(list)
    }

    override suspend fun getItunesTracks(albumId: Long): List<TrackDBModel> {
        return daoTracks.getItunesTracks(albumId).map { it.copy(it.typeAlbum + "_itunes") }
    }

    override suspend fun getLibraryTracks(albumId: Long): List<TrackDBModel> {
        return daoTracks.getLibraryTracks(albumId).map { it.copy(it.title + "_library") }
    }
}