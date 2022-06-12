package com.example.app


import android.app.Application
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.room.Room
import com.example.app.data.AlbumsRepository
import com.example.app.data.datastore.DataStoreAppFragmentStateSerializer
import com.example.app.data.datastore.DataStoreType
import com.example.app.data.db.AlbumsDbDataSource
import com.example.app.data.db.AlbumsRoomDataSource
import com.example.app.data.permission.StoragePermissionChecker
import com.example.app.data.permission.StoragePermissionCheckerImpl
import com.example.app.data.remote.*
import com.example.app.data.room.MyDatabase
import com.example.app.presentation.FragmentStateViewModel
import com.example.app.presentation.AlbumsViewModel
import com.example.app.presentation.TracksMyMusicViewModel
import com.example.app.presentation.TracksViewModel
import kotlinx.serialization.json.Json
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module

class MyApp : Application() {

    private val moduleDataStore: Module
        get() = module {

            factory<TracksMyMusicDataSource> { TracksMyMusicDataSourceImpl(get()) }
            single<Json> { Json }

            single(named(DataStoreType.FRAGMENT_STATE)) {
                DataStoreFactory.create(
                    DataStoreAppFragmentStateSerializer(get())
                ) {

                    applicationContext.preferencesDataStoreFile("application_prefs")
                }
            }
        }

    /* private val dbModule: Module
         get() = module {
             single<AlbumsDbDataSource> { AlbumsRoomDataSource(get()) }

             single {

                 Room.databaseBuilder(
                     get(),
                     MyDatabase::class.java, "database-itunes"
                 ).build()
             }

             *//*single {

                Room.databaseBuilder(
                    get(),
                    MyDatabase::class.java, "database-library"
                ).build()
            }*//*
        }
*/
    private val fakeRemoteModule: Module
        get() = module {
            factory<RemoteDataSource> { DataSourceFake() }
        }

    private val remoteModule: Module
        get() = module {
            factory<RemoteDataSource> { params -> RemoteDataSourceRetrofit(baseUrl = params.get()) }
        }


    private val viewModelModule: Module
        get() = module {
            single<StoragePermissionChecker> { params -> StoragePermissionCheckerImpl(params.get()) }
            factory { AlbumsRepository(get(), get(), isCacheEnabled = true) }
            viewModelOf(::AlbumsViewModel)
            viewModelOf(::TracksViewModel)
            viewModelOf(::TracksMyMusicViewModel)
            viewModel { FragmentStateViewModel(get(named(DataStoreType.FRAGMENT_STATE))) }
        }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApp)
            modules(fakeRemoteModule, viewModelModule/*, dbModule*/, moduleDataStore)
        }
    }
}