package com.github.krottv.tmstemp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.krottv.tmstemp.databinding.HostFragmentBinding

class HostFragmentBinder(val fragment: HostFragment) {

    lateinit var binding: HostFragmentBinding

    fun onCreateView(inflater: LayoutInflater,
                     container: ViewGroup?,
                     savedInstanceState: Bundle?
    ): View {
        binding = HostFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }
}