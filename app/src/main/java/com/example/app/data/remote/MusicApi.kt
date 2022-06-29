package com.example.app.data.remote

import com.example.app.domain.AlbumModel
import com.example.app.domain.TracksWithAlbums
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Streaming
import retrofit2.http.Url

interface MusicApi {
    @GET("api/?p=getItunesAlbums")
    suspend fun getItunesAlbums(): List<AlbumModel>

    @GET("api/?p=getItunesTracks")
    suspend fun getItunesTracks(@Query("albumId") albumId: Long): TracksWithAlbums

    @GET("api/?p=getLibraryAlbums")
    suspend fun getLibraryAlbums(): List<AlbumModel>

    @GET("api/?p=getLibraryTracks")
    suspend fun getLibraryTracks(@Query("albumId") albumId: Long): TracksWithAlbums

    @Streaming
    @GET
    suspend fun downloadFile(@Url fileUrl:String): Response<ResponseBody>
}