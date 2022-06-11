package com.example.app.data.remote

import com.github.krottv.tmstemp.domain.AlbumModel
import com.github.krottv.tmstemp.domain.Tracks
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Streaming
import retrofit2.http.Url

interface MusicApi {
    @GET("getAlbums")
    suspend fun getAlbums(): List<AlbumModel>

    @GET("getTrack")
    suspend fun getTracks(@Query("albumId") albumId: Long): Tracks

    @Streaming
    @GET
    suspend fun downloadFile(@Url fileUrl:String): Response<ResponseBody>
}