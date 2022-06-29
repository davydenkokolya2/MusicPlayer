package com.example.app.presentation.view.itunesfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app.presentation.view.albumsrecycler.AlbumsAdapter
import com.example.app.presentation.view.tracksrecycler.TracksAdapter
import com.example.app.domain.AlbumModel
import com.example.app.domain.TrackModel
import com.example.app.view.databinding.ItunesMusicFragmentBinding

class ITunesMusicFragmentBinder(
    val fragment: ITunesMusicFragment,
    val onItemClick: (View, TrackModel) -> Boolean
) {

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

    fun albumsLoaded(list: List<AlbumModel>?) {
        if (list != null)
            if (binding.albumsRecyclerITunes.adapter == null)
                binding.albumsRecyclerITunes.adapter = AlbumsAdapter(list)
            else
                (binding.albumsRecyclerITunes.adapter as AlbumsAdapter).data = list
    }


    fun tracksLoaded(list: List<TrackModel>?) {
        if (list != null) {
            if (binding.tracksRecyclerITunes.adapter == null)
                binding.tracksRecyclerITunes.adapter = TracksAdapter(list, onItemClick)
            else
                (binding.tracksRecyclerITunes.adapter as TracksAdapter).data = list
        }
    }
}