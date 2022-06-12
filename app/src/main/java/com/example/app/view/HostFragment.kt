package com.example.app.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.app.presentation.FragmentStateViewModel
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class HostFragment : Fragment() {

    lateinit var viewBinder: HostFragmentBinder
    private val fragmentStateViewModel: FragmentStateViewModel by inject()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinder = HostFragmentBinder(this)

        return viewBinder.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinder.binding.imageView.setOnClickListener {
            requireActivity().run {
                startActivity(Intent(this, SubscriptionActivity::class.java))
            }
        }

        viewBinder.binding.iTunes.setOnClickListener {
            fragmentStateViewModel.changeFragment(0)
        }

        viewBinder.binding.library.setOnClickListener {
            fragmentStateViewModel.changeFragment(1)
        }

        viewBinder.binding.myMusic.setOnClickListener {
            fragmentStateViewModel.changeFragment(2)
        }

        lifecycleScope.launch {
            fragmentStateViewModel.appFragmentState.collect {
                if (it != null)
                    viewBinder.changeFragment(it.numberOfFragment)
            }
        }
    }
}