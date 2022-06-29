package com.example.app.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.app.data.room.models.AlbumDBModel
import com.example.app.domain.RemoteApiType

@Dao
interface AlbumsDao {

    @Insert
    suspend fun saveAlbums(albums: List<AlbumDBModel>)

    @Query("SELECT * FROM albums where typeAlbum = 'itunes'")
    suspend fun getItunesAlbums(): List<AlbumDBModel>

    @Query("SELECT * FROM albums where typeAlbum = 'library'")
    suspend fun getLibraryAlbums(): List<AlbumDBModel>

    /*@Query("select * from albums where id = :albumId")
    fun findAlbumById(albumId: Long): Flow<AlbumDBModel?>*/

    /*@Transaction
    suspend fun savesAlbumsTenTime(albums: List<AlbumDBModel>) {
        for (i in 1..10) {
            saveAlbums(albums)
        }
    }*/

    /*@Query("select * from albums where id = :albumId")
    suspend fun getAlbumWithTracks(albumId: Long): TracksWithAlbumEntity*/
}