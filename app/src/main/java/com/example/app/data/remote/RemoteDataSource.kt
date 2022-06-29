package com.example.app.data.remote

import com.example.app.domain.AlbumModel
import com.example.app.domain.TrackModel
import com.example.app.domain.TracksWithAlbums
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Query
import retrofit2.http.Url

interface RemoteDataSource {
    suspend fun getItunesAlbums(): List<AlbumModel>

    suspend fun getItunesTracks(@Query("albumId") albumId: Long): TracksWithAlbums

    suspend fun getLibraryAlbums(): List<AlbumModel>

    suspend fun getLibraryTracks(@Query("albumId") albumId: Long): TracksWithAlbums

    suspend fun downloadFile(@Url fileUrl:String): Response<ResponseBody>
}