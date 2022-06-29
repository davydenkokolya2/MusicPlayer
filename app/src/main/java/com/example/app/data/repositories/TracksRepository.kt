package com.example.app.data.repositories

import com.example.app.data.room.db.AlbumsDbDataSource
import com.example.app.data.remote.RemoteDataSource
import com.example.app.data.room.models.TrackDBModel
import com.example.app.domain.RemoteApiType

class TracksRepository(
    private val albumsDbDataSource: AlbumsDbDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val isCacheEnabled: Boolean
) {

    suspend fun getTracks(remoteApiType: RemoteApiType, albumId: Long): List<TrackDBModel> {

        var cache = if (isCacheEnabled) when (remoteApiType) {
            RemoteApiType.LIBRARY -> albumsDbDataSource.getLibraryTracks(albumId)
            RemoteApiType.ITUNES -> albumsDbDataSource.getItunesTracks(albumId)
        } else null
        if (cache.isNullOrEmpty()) {
            cache = when (remoteApiType) {
                RemoteApiType.ITUNES -> remoteDataSource.getItunesTracks(albumId).tracks
                    .map { TrackDBModel( it.artist, it.image, it.title, it.url, albumId,"itunes") }

                RemoteApiType.LIBRARY -> remoteDataSource.getLibraryTracks(albumId).tracks
                    .map { TrackDBModel(it.artist, it.image, it.title, it.url, albumId, "library") }
            }
            albumsDbDataSource.saveTracks(cache)
        }
        //var cache = albumsRemoteDataSource.getAlbums(flag)
        return cache

    }
}