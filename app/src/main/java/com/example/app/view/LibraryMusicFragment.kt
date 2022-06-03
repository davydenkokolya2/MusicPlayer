package com.example.app.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.work.Data
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import coil.Coil.enqueue
import com.example.app.data.UploadMusicWorker
import com.github.krottv.tmstemp.R
import com.github.krottv.tmstemp.data.LibraryRemoteDataSourceRetrofit
import com.github.krottv.tmstemp.domain.Tracks
import com.github.krottv.tmstemp.presentation.AlbumsViewModel
import com.github.krottv.tmstemp.presentation.TracksViewModel
import com.github.krottv.tmstemp.view.LibraryMusicFragmentBinder
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import java.io.FileOutputStream
import java.io.InputStream

class LibraryMusicFragment : Fragment() {
    lateinit var viewBinder: LibraryMusicFragmentBinder
    private val viewModel: AlbumsViewModel = AlbumsViewModel(LibraryRemoteDataSourceRetrofit())
    private val tracksLibraryViewModel: TracksViewModel =
        TracksViewModel(LibraryRemoteDataSourceRetrofit())


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinder = LibraryMusicFragmentBinder(this, ::onItemClick)

        return viewBinder.onCreateView(inflater, container, savedInstanceState)
    }

    val libraryRemoteDataSourceRetrofit: LibraryRemoteDataSourceRetrofit =
        LibraryRemoteDataSourceRetrofit()


    fun onItemClick(view: View, tracks: Tracks): Boolean {

        val data = Data.Builder()
        data.putString("1", tracks.url)

        val uploadWork = OneTimeWorkRequestBuilder<UploadMusicWorker>().setInputData(data.build()).build()


        WorkManager.getInstance(requireContext()).enqueue(uploadWork)

        return true
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (parentFragment as NavHostFragment).parentFragment?.view?.findViewById<View>(R.id.iTunes)
            ?.setOnClickListener {
                val navController = findNavController()

                val action =
                    LibraryMusicFragmentDirections.actionLibraryMusicFragmentToITunesMusicFragment()
                navController.navigate(action)
            }

        viewModel.loadData()

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.stateITunes.collect {
                    viewBinder.onDataLoaded(it)
                }
            }
        }

        tracksLibraryViewModel.loadTracks()

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                tracksLibraryViewModel.stateITunes.collect {
                    viewBinder.tracksLoaded(it)
                }
            }
        }
    }
}