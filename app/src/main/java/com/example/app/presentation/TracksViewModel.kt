package com.github.krottv.tmstemp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app.data.remote.RemoteDataSourceRetrofit
import com.github.krottv.tmstemp.domain.Tracks
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TracksViewModel(val musicApi: RemoteDataSourceRetrofit): ViewModel() {

    private val _stateITunes = MutableStateFlow<Tracks?>(null)
    val stateITunes: StateFlow<Tracks?> = _stateITunes

    fun loadTracks() {
        viewModelScope.launch(Dispatchers.IO) {
            _stateITunes.emit(musicApi.getTracks(1))
        }
    }
}