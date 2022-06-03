package com.github.krottv.tmstemp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app.data.RemoteDataSourceRetrofit
import com.github.krottv.tmstemp.domain.AlbumModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AlbumsViewModel(val musicApi: RemoteDataSourceRetrofit): ViewModel(){

    private val _stateITunes = MutableStateFlow<List<AlbumModel>?>(null)
    val stateITunes: StateFlow<List<AlbumModel>?> = _stateITunes

    fun loadData() {
        viewModelScope.launch(Dispatchers.IO) {
            _stateITunes.emit(musicApi.getAlbums())
        }
    }


}