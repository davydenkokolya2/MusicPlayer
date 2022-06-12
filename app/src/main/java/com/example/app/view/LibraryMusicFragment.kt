package com.example.app.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.work.Data
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.app.data.UploadMusicWorker
import com.example.app.domain.TrackModel
import com.example.app.presentation.AlbumsViewModel
import com.example.app.presentation.FragmentStateViewModel
import com.example.app.presentation.TracksMyMusicViewModel
import com.example.app.presentation.TracksViewModel
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class LibraryMusicFragment : Fragment() {
    lateinit var viewBinder: LibraryMusicFragmentBinder
    private val albumsViewModel: AlbumsViewModel by inject()
    private val tracksViewModel: TracksViewModel by inject()
    private val fragmentStateViewModel: FragmentStateViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinder = LibraryMusicFragmentBinder(this, ::onItemClick)

        return viewBinder.onCreateView(inflater, container, savedInstanceState)
    }

    fun onItemClick(view: View, tracks: TrackModel): Boolean {

        val data = Data.Builder()
        data.putString("1", tracks.url)

        val uploadWork =
            OneTimeWorkRequestBuilder<UploadMusicWorker>().setInputData(data.build()).build()

        WorkManager.getInstance(requireContext()).enqueue(uploadWork)

        return true
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        albumsViewModel.loadAlbums(false)

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                fragmentStateViewModel.appFragmentState.collect {
                    if (it != null) {
                        val navController = findNavController()
                        if (it.numberOfFragment == 0) {
                            val action =
                                LibraryMusicFragmentDirections.actionLibraryMusicFragmentToITunesMusicFragment()
                            navController.navigate(action)
                        }
                        if (it.numberOfFragment == 2) {
                            val action =
                                LibraryMusicFragmentDirections.actionLibraryMusicFragmentToMyMusicFragment()
                            navController.navigate(action)
                        }
                    }
                }
            }
        }

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                albumsViewModel.stateITunes.collect {
                    viewBinder.albumsLoaded(it)
                }
            }
        }

        tracksViewModel.loadTracks()

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                tracksViewModel.stateITunes.collect {
                    viewBinder.tracksLoaded(it)
                }
            }
        }
    }
}

private const val BASE_URL = "https://us-central1-inspiry-2ee60.cloudfunctions.net/getLibraryAlbums"