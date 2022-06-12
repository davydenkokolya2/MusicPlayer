package com.example.app.data

import com.example.app.data.db.AlbumsDbDataSource
import com.example.app.data.remote.RemoteDataSource
import com.example.app.domain.AlbumModel

class AlbumsRepository(
    private val albumsDbDataSource: AlbumsDbDataSource,
    private val albumsRemoteDataSource: RemoteDataSource,
    private val isCacheEnabled: Boolean
) {

    suspend fun getAlbums(flag: Boolean): List<AlbumModel> {

        var cache = if (isCacheEnabled) albumsDbDataSource.getAlbums() else null
        if (cache.isNullOrEmpty()) {
            cache = albumsRemoteDataSource.getAlbums(flag)
            albumsDbDataSource.saveAlbums(cache)
        }
        //var cache = albumsRemoteDataSource.getAlbums(flag)
        return cache
    }
}