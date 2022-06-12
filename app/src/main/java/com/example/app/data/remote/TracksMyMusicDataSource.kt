package com.example.app.data.remote

import com.example.app.domain.TrackModel

interface TracksMyMusicDataSource {
    suspend fun getTracks(): List<TrackModel>
}