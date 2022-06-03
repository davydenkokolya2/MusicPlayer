package com.github.krottv.tmstemp

import android.app.Application
import com.example.app.data.RemoteDataSourceRetrofit
import com.example.app.view.DownloadNotification
import com.github.krottv.tmstemp.data.ITunesRemoteDataSourceRetrofit
import com.github.krottv.tmstemp.data.LibraryRemoteDataSourceRetrofit
import com.github.krottv.tmstemp.presentation.AlbumsViewModel
import com.github.krottv.tmstemp.presentation.TracksViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

class MyApp : Application() {

    private val itunesModule: Module
        get() = module {
            factoryOf <RemoteDataSourceRetrofit>(::ITunesRemoteDataSourceRetrofit)
        }
    private val libraryModule: Module
        get() = module {
            factoryOf <RemoteDataSourceRetrofit>(::LibraryRemoteDataSourceRetrofit)
        }
    private val viewModelModule: Module
        get() = module {
            viewModelOf (::AlbumsViewModel)
            viewModelOf (::TracksViewModel)
        }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApp)
            modules(libraryModule, itunesModule, viewModelModule)
        }
    }
}