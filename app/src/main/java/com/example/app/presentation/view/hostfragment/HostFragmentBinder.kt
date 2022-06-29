package com.example.app.presentation.view.hostfragment

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
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
        when (numberFragment) {
            0 -> {
                setWhiteColor(binding.iTunes)
                setGrayColor(binding.myMusic)
                setGrayColor(binding.library)
            }
            1 -> {
                setWhiteColor(binding.library)
                setGrayColor(binding.myMusic)
                setGrayColor(binding.iTunes)
            }
            2 -> {
                setWhiteColor(binding.myMusic)
                setGrayColor(binding.library)
                setGrayColor(binding.iTunes)
            }
            3 -> {
                setGrayColor(binding.myMusic)
                setGrayColor(binding.library)
                setGrayColor(binding.iTunes)
            }
        }
    }

    private fun setGrayColor(view: TextView) {
        view.setTextColor(Color.GRAY)
        view.textSize - 16F
        view.typeface = Typeface.DEFAULT
    }

    private fun setWhiteColor(view: TextView) {
        view.setTextColor(Color.WHITE)
        view.textSize - 18F
        view.typeface = Typeface.DEFAULT_BOLD
    }
}