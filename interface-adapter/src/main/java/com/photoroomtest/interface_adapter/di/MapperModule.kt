package com.photoroomtest.interface_adapter.di

import com.photoroomtest.interface_adapter.weather.model.GalleryImageUiModel
import com.photoroomtest.interface_adapter.weather.model.GalleryRemote
import org.koin.dsl.module.module

val mapperModule = module {
    single { GalleryImageUiModel.Mapper() }
    single { GalleryRemote.Mapper() }


}
