package com.example.app


import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.room.Room
import com.example.app.data.repositories.AlbumsRepository
import com.example.app.data.repositories.TracksRepository
import com.example.app.data.datastore.DataStoreAppFragmentStateSerializer
import com.example.app.data.datastore.DataStoreType
import com.example.app.data.room.db.AlbumsDbDataSource
import com.example.app.data.room.db.AlbumsRoomDataSource
import com.example.app.data.permission.StoragePermissionChecker
import com.example.app.data.permission.StoragePermissionCheckerImpl
import com.example.app.data.remote.*
import com.example.app.data.room.MyDatabase
import com.example.app.domain.purchase.PurchaseMakeInteractor
import com.example.app.domain.purchase.PurchaseMakerInteractorFake
import com.example.app.domain.purchase.PurchaseStateInteractor
import com.example.app.domain.purchase.PurchaseStateInteractorFake
import com.example.app.presentation.viewmodel.*
import kotlinx.serialization.json.Json
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.qualifier.named
import org.koin.dsl.module

class MyApp : Application() {

    private val moduleDataStore: Module
        get() = module {
            single<TracksMyMusicDataSource> { TracksMyMusicDataSourceImpl(get()) }
            single<Json> { Json }

            single(named(DataStoreType.FRAGMENT_STATE)) {
                DataStoreFactory.create(
                    DataStoreAppFragmentStateSerializer(get())
                ) {

                    applicationContext.preferencesDataStoreFile("application_prefs")
                }
            }
        }

    private val dbModule: Module
        get() = module {
            single<AlbumsDbDataSource> { AlbumsRoomDataSource(get()) }

            single {
                Room.databaseBuilder(
                    get(),
                    MyDatabase::class.java, "myDatabase"
                ).build()
            }

        }

    private val remoteModule: Module
        get() = module {
            single<RemoteDataSource> { RemoteDataSourceRetrofit() }
        }

    private val moduleSharedPreferences: Module
        get() = module {
            single {
                val context: Context = get()
                context.getSharedPreferences("my_preferences", Context.MODE_PRIVATE)
            }
        }

    private val viewModelModule: Module
        get() = module {
            single<StoragePermissionChecker> { params -> StoragePermissionCheckerImpl(params.get()) }
            single { AlbumsRepository(get(), get(), isCacheEnabled = true) }
            single { TracksRepository(get(), get(), isCacheEnabled = true) }
            viewModelOf(::PurchaseViewModel)
            viewModelOf(::AlbumsViewModel)
            viewModelOf(::TracksViewModel)
            viewModelOf(::TracksMyMusicViewModel)
            viewModel {
                FragmentStateViewModel(
                    get(named(DataStoreType.FRAGMENT_STATE)),
                    applicationContext
                )
            }
        }

    private val modulePurchases: Module
        get() = module {

            singleOf(::PurchaseStateInteractorFake) {
                bind<PurchaseStateInteractor>()
            }

            singleOf(::PurchaseMakerInteractorFake) {
                bind<PurchaseMakeInteractor>()
            }
        }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApp)
            modules(
                remoteModule,
                viewModelModule,
                dbModule,
                moduleDataStore,
                modulePurchases,
                moduleSharedPreferences
            )
        }
    }
}