package com.example.app.view

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.app.view.databinding.HostFragmentBinding


class HostFragmentBinder(val fragment: HostFragment) {

    lateinit var binding: HostFragmentBinding

    fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = HostFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }


    fun changeFragment(numberFragment: Int) {
        Log.i("test", numberFragment.toString())
        when (numberFragment) {
            0 -> {
                binding.iTunes.setTextColor(Color.WHITE)
                binding.iTunes.textSize = 18F
                binding.library.setTextColor(Color.GRAY)
                binding.library.textSize = 16F
                binding.myMusic.setTextColor(Color.GRAY)
                binding.myMusic.textSize - 16F
            }
            1 -> {
                binding.library.setTextColor(Color.WHITE)
                binding.library.textSize = 18F
                binding.iTunes.setTextColor(Color.GRAY)
                binding.iTunes.textSize = 16F
                binding.myMusic.setTextColor(Color.GRAY)
                binding.myMusic.textSize - 16F
            }
            2 -> {
                binding.myMusic.setTextColor(Color.WHITE)
                binding.myMusic.textSize - 18F
                binding.library.setTextColor(Color.GRAY)
                binding.library.textSize = 16F
                binding.iTunes.setTextColor(Color.GRAY)
                binding.iTunes.textSize = 16F
            }
        }
    }
}