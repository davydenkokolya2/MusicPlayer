package com.example.app.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.github.krottv.tmstemp.domain.AlbumModel
import com.github.krottv.tmstemp.domain.TrackModel

@Database(entities = [AlbumModel::class, TrackModel::class], version = 1)
abstract class MyDatabase: RoomDatabase() {
    abstract fun provideDao(): AlbumsDao
    abstract fun provideDaoTracks(): TracksDao
}