package com.example.app.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app.data.repositories.AlbumsRepository
import com.example.app.domain.AlbumModel
import com.example.app.domain.RemoteApiType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AlbumsViewModel(val musicApi: AlbumsRepository): ViewModel(){

    private val _stateITunes = MutableStateFlow<List<AlbumModel>?>(null)
    val stateITunes: StateFlow<List<AlbumModel>?> = _stateITunes

    fun loadAlbums(remoteApiType: RemoteApiType) {
        viewModelScope.launch(Dispatchers.IO) {
            _stateITunes.emit(musicApi.getAlbums(remoteApiType).map { AlbumModel(it.id, it.image, it.name, it.trackCount) })
        }
    }


}