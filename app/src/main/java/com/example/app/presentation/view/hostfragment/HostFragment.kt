package com.example.app.presentation.view.hostfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.lifecycle.lifecycleScope
import com.example.app.domain.purchase.PurchaseStateInteractor
import com.example.app.presentation.view.itunesfragment.ITunesMusicFragment
import com.example.app.presentation.view.libraryfragment.LibraryMusicFragment
import com.example.app.presentation.view.mymusicfragment.MyMusicFragment
import com.example.app.presentation.view.purchasefragment.PurchaseFragment
import com.example.app.presentation.viewmodel.FragmentStateViewModel
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class HostFragment : Fragment() {

    lateinit var viewBinder: HostFragmentBinder
    private val fragmentStateViewModel: FragmentStateViewModel by sharedViewModel()
    private val purchaseStateInteractor: PurchaseStateInteractor by inject()

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
                if (it != null) {
                    viewBinder.changeFragment(it.numberOfFragment)

                    when (it.numberOfFragment) {
                        0 -> childFragmentManager.commit {
                            replace(
                                viewBinder.binding.fragmentContainerView2.id,
                                ITunesMusicFragment()
                            )
                        }
                        1 -> childFragmentManager.commit {
                            replace(
                                viewBinder.binding.fragmentContainerView2.id,
                                LibraryMusicFragment()
                            )
                        }
                        2 -> childFragmentManager.commit {
                            replace(
                                viewBinder.binding.fragmentContainerView2.id,
                                MyMusicFragment()
                            )
                        }
                        3 -> childFragmentManager.commit {
                            replace(
                                viewBinder.binding.fragmentContainerView2.id,
                                PurchaseFragment()
                            )
                        }
                    }
                }
            }
        }

        lifecycleScope.launch {
            purchaseStateInteractor.isPremium.collect {
                if (it) {
                    viewBinder.binding.purchaseIcon.visibility = View.GONE
                } else {
                    viewBinder.binding.purchaseIcon.visibility = View.VISIBLE

                    viewBinder.binding.purchaseIcon.setOnClickListener {
                        fragmentStateViewModel.changeFragment(3)
                    }
                }
            }
        }
    }

}
