package com.example.app.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.app.data.room.models.AlbumDBModel
import com.example.app.data.room.models.TrackDBModel

@Database(entities = [AlbumDBModel::class, TrackDBModel::class], version = 1)
abstract class MyDatabase: RoomDatabase() {
    abstract fun provideDao(): AlbumsDao
    abstract fun provideDaoTracks(): TracksDao
}