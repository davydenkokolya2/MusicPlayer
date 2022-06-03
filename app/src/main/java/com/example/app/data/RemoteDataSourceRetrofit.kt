package com.example.app.data

import com.github.krottv.tmstemp.domain.AlbumModel
import com.github.krottv.tmstemp.domain.TracksModel
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Query
import retrofit2.http.Url

interface RemoteDataSourceRetrofit {
    suspend fun getAlbums(): List<AlbumModel>

    suspend fun getTracks(@Query("albumId") albumId: Long): TracksModel

    suspend fun downloadFile(@Url fileUrl:String): Response<ResponseBody>
}