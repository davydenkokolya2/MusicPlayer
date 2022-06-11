package com.example.app.data

import android.provider.ContactsContract
import com.example.app.data.db.AlbumsDbDataSource
import com.example.app.data.remote.DataSourceFake
import com.example.app.data.remote.RemoteDataSourceRetrofit
import com.github.krottv.tmstemp.domain.AlbumModel

class AlbumsRepository(
    private val albumsDbDataSource: AlbumsDbDataSource,
    private val albumsRemoteDataSource: RemoteDataSourceRetrofit,
    private val isCacheEnabled: Boolean
) {

    suspend fun getAlbums(): List<AlbumModel> {

        var cache = if (isCacheEnabled) albumsDbDataSource.getAlbums() else null
        if (cache.isNullOrEmpty()) {
            cache = albumsRemoteDataSource.getAlbums()
            albumsDbDataSource.saveAlbums(cache)
        }

        return cache
    }
}