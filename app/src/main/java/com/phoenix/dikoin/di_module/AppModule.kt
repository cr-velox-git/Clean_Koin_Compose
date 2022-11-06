package com.phoenix.dikoin.di_module

import androidx.room.Room
import com.phoenix.dikoin.data.localdatabase.NewsDao
import com.phoenix.dikoin.data.localdatabase.NewsDataBase
import com.phoenix.dikoin.data.remote.MyApi
import com.phoenix.dikoin.data.repositories.MainRepositoryImp
import com.phoenix.dikoin.data.repositories.implementation.LocalDataSourceImp
import com.phoenix.dikoin.data.repositories.implementation.NetworkDataSourceImp
import com.phoenix.dikoin.data.repositories.source.LocalDataSource
import com.phoenix.dikoin.data.repositories.source.NetworkDataSource
import com.phoenix.dikoin.domain.repositories.MainRepository
import com.phoenix.dikoin.domain.usecase.GetHeadLineUseCase
import com.phoenix.dikoin.ui.viewmodel.MainViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val appModule = module {
    //This all are {DEPENDENCY}

    //telling what to create
    single {
//same as Singleton

        val base = com.phoenix.dikoin.BuildConfig.BASE_URL
        Retrofit.Builder()
            .baseUrl(base)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(MyApi::class.java)
    }

    single {
        Room.databaseBuilder(
            androidApplication().applicationContext,
            NewsDataBase::class.java,
            "newsDb"
        ).fallbackToDestructiveMigration().build()
    }

    /**
     * get() says I already have it, so find it and use it,
     * in this case MyApi Retrofit Instance
     * */
    //telling where to put
    //we need to specify type
    // this will create single instances for all view model or where ever we need
    //as abstraction by specifying the type

    single<NetworkDataSource> {
        NetworkDataSourceImp(get<MyApi>())
    }

    single<NewsDao> {
        get<NewsDataBase>().newsDao()
    }
    single<LocalDataSource> {
        LocalDataSourceImp(get<NewsDao>())
    }


    single<MainRepository> {
        MainRepositoryImp(get<NetworkDataSource>(), get<LocalDataSource>())
    }

    single {
        GetHeadLineUseCase(get<MainRepository>())
    }

    /**
     * it will create instance everytime object is requested
     * */
//    factory {
////        MainRepositoryImp(get()) //getting the MyApi interface
//        //if we have 2 view model that required diff instances of same api
//        // it will create 2 diff instances
//    }

    /**
     * creating the view model
     * */

    viewModel {
        MainViewModel(get<GetHeadLineUseCase>())
    }


}