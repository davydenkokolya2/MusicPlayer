package com.example.app.data.repositories

import com.example.app.data.room.db.AlbumsDbDataSource
import com.example.app.data.remote.RemoteDataSource
import com.example.app.data.room.models.AlbumDBModel
import com.example.app.domain.RemoteApiType

class AlbumsRepository(
    private val albumsDbDataSource: AlbumsDbDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val isCacheEnabled: Boolean
) {

    suspend fun getAlbums(remoteApiType: RemoteApiType): List<AlbumDBModel> {

        var cache = if (isCacheEnabled) when (remoteApiType) {
            RemoteApiType.LIBRARY -> albumsDbDataSource.getLibraryAlbums()
            RemoteApiType.ITUNES -> albumsDbDataSource.getItunesAlbums()
        } else null
        if (cache.isNullOrEmpty()) {
            cache = when (remoteApiType) {
                RemoteApiType.ITUNES -> remoteDataSource.getItunesAlbums()
                    .map { AlbumDBModel(it.id, it.image, it.name, it.trackCount, "itunes") }

                RemoteApiType.LIBRARY -> remoteDataSource.getLibraryAlbums()
                    .map { AlbumDBModel(it.id, it.image, it.name, it.trackCount, "library") }
            }
            albumsDbDataSource.saveAlbums(cache)
        }
        //var cache = albumsRemoteDataSource.getAlbums(flag)
        return cache

    }
}