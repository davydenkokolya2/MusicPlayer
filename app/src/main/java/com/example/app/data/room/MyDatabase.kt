package com.example.app.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.app.domain.AlbumModel
import com.example.app.domain.TrackModel

@Database(entities = [AlbumModel::class, TrackModel::class], version = 1)
abstract class MyDatabase: RoomDatabase() {
    abstract fun provideDao(): AlbumsDao
    abstract fun provideDaoTracks(): TracksDao
}