package com.github.krottv.tmstemp.data

import com.github.krottv.tmstemp.domain.AlbumModel
import com.github.krottv.tmstemp.domain.TracksModel
import retrofit2.http.GET
import retrofit2.http.Query

interface MusicApi {
    @GET("getAlbums")
    suspend fun getAlbums(): List<AlbumModel>

    @GET("getTrack")
    suspend fun getTracks(@Query("albumId") albumId: Long): TracksModel
}