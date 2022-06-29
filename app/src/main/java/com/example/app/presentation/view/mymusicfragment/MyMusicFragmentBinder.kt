package com.example.app.presentation.view.mymusicfragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app.domain.TrackModel
import com.example.app.view.databinding.MyMusicFragmentBinding
import com.example.app.presentation.view.tracksrecycler.TracksAdapter

class MyMusicFragmentBinder(val fragment: Fragment) {

    lateinit var binding: MyMusicFragmentBinding


    fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MyMusicFragmentBinding.inflate(inflater, container, false)

        val layoutManager =
            LinearLayoutManager(fragment.requireActivity())

        binding.musicRecyclerMyMusic.layoutManager = layoutManager

        return binding.root
    }

    fun onItemClick(view: View, item: TrackModel): Boolean {
        return false
    }

    fun onDataLoaded(list: List<TrackModel>) {
        Log.i("test", "asdfdsf")
        if (binding.musicRecyclerMyMusic.adapter == null) {
            binding.musicRecyclerMyMusic.adapter = TracksAdapter(list, ::onItemClick)
        } else {
            (binding.musicRecyclerMyMusic.adapter as TracksAdapter).data = list
        }
    }
}