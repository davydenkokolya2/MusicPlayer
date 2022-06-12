package com.example.app.data.remote

import com.example.app.domain.AlbumModel
import com.example.app.domain.Tracks
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Query
import retrofit2.http.Url

interface RemoteDataSource {
    suspend fun getAlbums(flag: Boolean): List<AlbumModel>

    suspend fun getTracks(@Query("albumId") albumId: Long): Tracks

    suspend fun downloadFile(@Url fileUrl:String): Response<ResponseBody>
}