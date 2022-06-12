package com.example.app.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app.data.AlbumsRepository
import com.example.app.data.remote.RemoteDataSource
import com.example.app.domain.AlbumModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AlbumsViewModel(val musicApi: RemoteDataSource): ViewModel(){

    private val _stateITunes = MutableStateFlow<List<AlbumModel>?>(null)
    val stateITunes: StateFlow<List<AlbumModel>?> = _stateITunes

    fun loadAlbums(flag: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            _stateITunes.emit(musicApi.getAlbums(flag))
        }
    }


}