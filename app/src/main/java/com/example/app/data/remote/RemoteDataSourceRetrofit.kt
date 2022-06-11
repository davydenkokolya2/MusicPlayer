package com.example.app.data.remote

import com.github.krottv.tmstemp.domain.AlbumModel
import com.github.krottv.tmstemp.domain.Tracks
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Query
import retrofit2.http.Url

interface RemoteDataSourceRetrofit {
    suspend fun getAlbums(): List<AlbumModel>

    suspend fun getTracks(@Query("albumId") albumId: Long): Tracks

    suspend fun downloadFile(@Url fileUrl:String): Response<ResponseBody>
}