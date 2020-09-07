package com.photoroomtest.application.di

import com.photoroomtest.gallery.data.local.WeatherLocalDataSourceImpl
import com.photoroomtest.gallery.data.remote.WeatherRemoteDataSourceImpl
import com.photoroomtest.interface_adapter.di.interfaceAdapterModule
import com.photoroomtest.interface_adapter.di.mapperModule
import com.photoroomtest.interface_adapter.weather.GalleryViewModel
import com.photoroomtest.interface_adapter.weather.HourlyWeatherViewModel
import com.photoroomtest.use_case.di.useCaseModule
import com.photoroomtest.use_case.weather.data.source.WeatherLocalDataSource
import com.photoroomtest.use_case.weather.data.source.WeatherRemoteDataSource
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val applicationModule = module {
    viewModel {
        GalleryViewModel(
            get()
        )
    }
    viewModel {
        HourlyWeatherViewModel(
            get()
        )
    }

    single { WeatherRemoteDataSourceImpl(
        get(),
        get()
    ) as WeatherRemoteDataSource
    }
    single { WeatherLocalDataSourceImpl() as WeatherLocalDataSource }
}

val applicationInjectionsModules = listOf(
    applicationModule,
    retrofitModule,
    retrofitDependenciesModule,
    interfaceAdapterModule,
    useCaseModule,
    mapperModule
)