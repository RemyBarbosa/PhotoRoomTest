package com.photoroomtest.interface_adapter.di

import com.photoroomtest.interface_adapter.weather.GalleryManager
import org.koin.dsl.module.module

val interfaceAdapterModule = module {
    single {
        GalleryManager(
            get(),
            get()
        )
    }
}
