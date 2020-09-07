package com.photoroomtest.application.di

import com.photoroomtest.gallery.data.remote.GalleryRemoteDataSourceImpl
import com.photoroomtest.interface_adapter.di.interfaceAdapterModule
import com.photoroomtest.interface_adapter.di.mapperModule
import com.photoroomtest.interface_adapter.weather.GalleryViewModel
import com.photoroomtest.use_case.di.useCaseModule
import com.photoroomtest.use_case.weather.data.source.GalleryRemoteDataSource
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val applicationModule = module {
    viewModel {
        GalleryViewModel(
            get()
        )
    }

    single { GalleryRemoteDataSourceImpl(get(), get()) as GalleryRemoteDataSource }
}

val applicationInjectionsModules = listOf(
    applicationModule,
    retrofitModule,
    retrofitDependenciesModule,
    interfaceAdapterModule,
    useCaseModule,
    mapperModule
)