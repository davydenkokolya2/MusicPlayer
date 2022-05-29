package com.github.krottv.tmstemp.data

import android.util.Log
import com.github.krottv.tmstemp.domain.AlbumModel
import com.github.krottv.tmstemp.domain.Tracks
import com.github.krottv.tmstemp.domain.TracksModel
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.create

class ITunesRemoteDataSourceRetrofit : MusicApi {

    override suspend fun getAlbums(): List<AlbumModel> {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://us-central1-inspiry-2ee60.cloudfunctions.net/getItunesAlbums/")
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()

        val musicApi: MusicApi = retrofit.create()
        return musicApi.getAlbums()
    }

    override suspend fun getTracks(albumId: Long): TracksModel {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://us-central1-inspiry-2ee60.cloudfunctions.net/getItunesTracks/")
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()

        val musicApi: MusicApi = retrofit.create()

        return musicApi.getTracks(1)
    }
}