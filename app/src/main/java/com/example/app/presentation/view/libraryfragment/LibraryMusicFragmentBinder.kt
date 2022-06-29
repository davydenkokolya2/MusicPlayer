package com.example.app.presentation.view.libraryfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app.presentation.view.albumsrecycler.AlbumsAdapter
import com.example.app.presentation.view.tracksrecycler.TracksAdapter
import com.example.app.domain.AlbumModel
import com.example.app.domain.TrackModel
import com.example.app.view.databinding.LibraryMusicFragmentBinding

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

    fun tracksLoaded(list: List<TrackModel>?) {

        if (list != null) {
            if (binding.tracksRecyclerAlbum.adapter == null)
                binding.tracksRecyclerAlbum.adapter = TracksAdapter(list, onItemClick)
            else
                (binding.tracksRecyclerAlbum.adapter as TracksAdapter).data = list
        }
    }

}