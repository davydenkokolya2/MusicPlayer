package com.example.app.presentation

import androidx.datastore.core.DataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app.data.datastore.AppFragmentState
import com.github.krottv.tmstemp.domain.AlbumModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class FragmentStateViewModel(private val appFragment: DataStore<AppFragmentState>): ViewModel(){

    val appFragmentState: StateFlow<AppFragmentState?> =
        appFragment.data.stateIn(viewModelScope, SharingStarted.Lazily, null)

    fun changeFragment(value: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            appFragment.updateData { it.copy(numberOfFragment = value) }
        }
    }
}