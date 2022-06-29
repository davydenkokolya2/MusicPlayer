package com.example.app.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app.data.repositories.TracksRepository
import com.example.app.domain.RemoteApiType
import com.example.app.domain.TrackModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TracksViewModel(val musicApi: TracksRepository): ViewModel() {

    private val _stateITunes = MutableStateFlow<List<TrackModel>?>(null)
    val stateITunes: StateFlow<List<TrackModel>?> = _stateITunes

    fun loadTracks(remoteApiType: RemoteApiType) {
        viewModelScope.launch(Dispatchers.IO) {
            _stateITunes.emit(musicApi.getTracks(remoteApiType, 1).map { TrackModel(it.artist, it.image, it.title, it.url) })
        }
    }
}