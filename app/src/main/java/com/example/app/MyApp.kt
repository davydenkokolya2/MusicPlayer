package com.github.krottv.tmstemp

import android.app.Application
import com.github.krottv.tmstemp.data.ITunesRemoteDataSourceRetrofit
import com.github.krottv.tmstemp.data.LibraryRemoteDataSourceRetrofit
import com.github.krottv.tmstemp.data.MusicApi
import com.github.krottv.tmstemp.presentation.AlbumsViewModel
import com.github.krottv.tmstemp.presentation.TracksViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

class MyApp : Application() {

    private val itunesModule: Module
        get() = module {
            factory<MusicApi> { ITunesRemoteDataSourceRetrofit() }
        }
    private val libraryModule: Module
        get() = module {
            factory<MusicApi> { LibraryRemoteDataSourceRetrofit() }
        }
    private val viewModelModule: Module
        get() = module {
            viewModel { AlbumsViewModel(get()) }
            viewModel { TracksViewModel(get()) }
        }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApp)
            modules(libraryModule, itunesModule, viewModelModule)
        }
    }
}