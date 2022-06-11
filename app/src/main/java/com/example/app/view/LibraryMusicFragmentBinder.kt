package com.example.app.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app.view.LibraryMusicFragment
import com.example.app.view.albumsrecycler.AlbumsAdapter
import com.example.app.view.tracksrecycler.TracksAdapter
import com.github.krottv.tmstemp.databinding.LibraryMusicFragmentBinding
import com.github.krottv.tmstemp.domain.AlbumModel
import com.github.krottv.tmstemp.domain.TrackModel
import com.github.krottv.tmstemp.domain.Tracks

class LibraryMusicFragmentBinder(
    val fragment: LibraryMusicFragment,
    val onItemClick: (View, TrackModel) -> Boolean
) {

    lateinit var binding: LibraryMusicFragmentBinding

    fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = LibraryMusicFragmentBinding.inflate(inflater, container, false)

        var layoutManager =
            LinearLayoutManager(fragment.requireActivity(), LinearLayoutManager.HORIZONTAL, false)

        binding.albumsRecyclerLibrary.layoutManager = layoutManager

        layoutManager =
            LinearLayoutManager(fragment.requireActivity())

        binding.tracksRecyclerAlbum.layoutManager = layoutManager

        return binding.root
    }

    fun albumsLoaded(list: List<AlbumModel>?) {

        if (list != null)
            if (binding.albumsRecyclerLibrary.adapter == null)
                binding.albumsRecyclerLibrary.adapter = AlbumsAdapter(list)
            else
                (binding.albumsRecyclerLibrary.adapter as AlbumsAdapter).data = list
    }

    fun tracksLoaded(list: Tracks?) {

        if (list != null) {

            if (binding.tracksRecyclerAlbum.adapter == null)
                binding.tracksRecyclerAlbum.adapter = TracksAdapter(list.tracks, onItemClick)
            else
                (binding.tracksRecyclerAlbum.adapter as TracksAdapter).data = list.tracks
        }
    }

}