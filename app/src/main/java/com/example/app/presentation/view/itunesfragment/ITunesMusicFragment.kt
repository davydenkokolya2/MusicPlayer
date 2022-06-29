package com.example.app.presentation.view.itunesfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.work.Data
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.app.data.UploadMusicWorker
import com.example.app.domain.RemoteApiType
import com.example.app.domain.TrackModel
import com.example.app.presentation.viewmodel.AlbumsViewModel
import com.example.app.presentation.viewmodel.TracksViewModel
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class ITunesMusicFragment : Fragment() {

    lateinit var viewBinder: ITunesMusicFragmentBinder
    private val albumsViewModel: AlbumsViewModel by inject()
    private val tracksViewModel: TracksViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinder = ITunesMusicFragmentBinder(this, ::onItemClick)

        return viewBinder.onCreateView(inflater, container, savedInstanceState)
    }

    fun onItemClick(view: View, tracks: TrackModel): Boolean {

        val data = Data.Builder()
        data.putString("1", tracks.url)
        //data.putString("2", BASE_URL)

        val uploadWork =
            OneTimeWorkRequestBuilder<UploadMusicWorker>().setInputData(data.build()).build()

        WorkManager.getInstance(requireContext()).enqueue(uploadWork)

        return true
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        albumsViewModel.loadAlbums(RemoteApiType.ITUNES)

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                albumsViewModel.stateITunes.collect {
                    viewBinder.albumsLoaded(it)
                }

            }
        }

        tracksViewModel.loadTracks(RemoteApiType.ITUNES)

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                tracksViewModel.stateITunes.collect {

                    viewBinder.tracksLoaded(it)
                }
            }
        }
    }
}

private const val BASE_URL = "https://us-central1-inspiry-2ee60.cloudfunctions.net/getItunesAlbums"