package com.photoroomtest.interface_adapter.di

import com.photoroomtest.interface_adapter.weather.WeatherManager
import org.koin.dsl.module.module

val interfaceAdapterModule = module {
    single {
        WeatherManager(
            get(),
            get(),
            get()
        )
    }
}
