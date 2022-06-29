package com.example.app.presentation.view.mymusicfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.app.data.permission.PermissionState
import com.example.app.data.permission.StoragePermissionChecker
import com.example.app.presentation.viewmodel.TracksMyMusicViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class MyMusicFragment : Fragment() {

    lateinit var myMusicFragmentBinder: MyMusicFragmentBinder
    private val viewModel: TracksMyMusicViewModel by inject()
    val storagePermissionChecker: StoragePermissionChecker by inject { parametersOf(requireActivity()) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        myMusicFragmentBinder = MyMusicFragmentBinder(this)

        return myMusicFragmentBinder.onCreateView(inflater, container, savedInstanceState)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            storagePermissionChecker.storagePermission.collectLatest { permissionState ->
                //Log.i("test", permissionState.toString())
                when (permissionState) {
                    PermissionState.HAS_PERMISSION -> {
                        loadAndObserveData()
                    }
                    PermissionState.NO_PERMISSION -> {
                        storagePermissionChecker.startPermissionDialog()
                    }
                    PermissionState.REJECTED_ASK_AGAIN -> {
                        storagePermissionChecker.startPermissionDialog()
                    }
                    PermissionState.REJECTED_FOREVER -> {
                        // nothing
                    }
                }
            }
        }
    }


    private suspend fun loadAndObserveData() {
        viewModel.loadTracks()
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.stateITunes.collect {
                    if (it != null) {
                        //Log.i("test", "collectData $it")
                        myMusicFragmentBinder.onDataLoaded(it)

                        (view?.parent as? ViewGroup)?.doOnPreDraw {
                            startPostponedEnterTransition()
                        }
                    }
                }
            }
        }
    }
}

