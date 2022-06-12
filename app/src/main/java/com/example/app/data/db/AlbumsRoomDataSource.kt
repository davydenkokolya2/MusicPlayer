package com.example.app.data.db

import com.example.app.data.room.MyDatabase
import com.example.app.domain.AlbumModel

class AlbumsRoomDataSource(database: MyDatabase): AlbumsDbDataSource {

    private val dao = database.provideDao()

    override suspend fun saveAlbums(list: List<AlbumModel>) {
        dao.saveAlbums(list)
    }

    override suspend fun getAlbums(): List<AlbumModel> {
        return dao.getAlbums().map { it.copy(name = it.name + "_room") }
    }
}