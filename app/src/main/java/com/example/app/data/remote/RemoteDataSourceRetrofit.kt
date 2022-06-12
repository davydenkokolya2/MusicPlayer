package com.example.app.data.remote

import com.example.app.domain.AlbumModel
import com.example.app.domain.Tracks
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.create


class RemoteDataSourceRetrofit(private val baseUrl: String) : RemoteDataSource {

    private fun createRetrofit(): MusicApi {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()

        return retrofit.create()
    }

    override suspend fun getAlbums(flag: Boolean): List<AlbumModel> {

        return createRetrofit().getAlbums()
    }

    override suspend fun getTracks(albumId: Long): Tracks {

        return createRetrofit().getTracks(1)
    }

    override suspend fun downloadFile(fileUrl: String): Response<ResponseBody> {


        return createRetrofit().downloadFile(fileUrl);
    }
}