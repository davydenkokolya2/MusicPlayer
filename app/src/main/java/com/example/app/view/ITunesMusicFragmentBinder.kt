package com.github.krottv.tmstemp.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.krottv.tmstemp.databinding.ItunesMusicFragmentBinding
import com.github.krottv.tmstemp.domain.AlbumModel
import com.github.krottv.tmstemp.domain.Tracks
import com.github.krottv.tmstemp.domain.TracksModel

class ITunesMusicFragmentBinder(val fragment: ITunesMusicFragment) {

    lateinit var binding: ItunesMusicFragmentBinding

    fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ItunesMusicFragmentBinding.inflate(inflater, container, false)

        var layoutManager =
            LinearLayoutManager(fragment.requireActivity(), LinearLayoutManager.HORIZONTAL, false)

        binding.albumsRecyclerITunes.layoutManager = layoutManager

        layoutManager =
            LinearLayoutManager(fragment.requireActivity())

        binding.tracksRecyclerITunes.layoutManager = layoutManager

        return binding.root
    }

    fun onDataLoaded(list: List<AlbumModel>?) {
        if (list != null)
            if (binding.albumsRecyclerITunes.adapter == null)
                binding.albumsRecyclerITunes.adapter = AlbumsAdapter(list)
            else
                (binding.albumsRecyclerITunes.adapter as AlbumsAdapter).data = list
    }
    fun onItemClick(view: View, tracks: Tracks): Boolean {
        Log.i("Test", tracks.title.toString())
        return true
    }
    fun tracksLoaded(list: TracksModel?) {

        if (list != null) {
            if (binding.tracksRecyclerITunes.adapter == null)
                binding.tracksRecyclerITunes.adapter = TracksAdapter(list.tracks, ::onItemClick)
            else
                (binding.tracksRecyclerITunes.adapter as TracksAdapter).data = list.tracks
        }
    }
}