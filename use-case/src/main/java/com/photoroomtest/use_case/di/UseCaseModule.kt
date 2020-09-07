package com.photoroomtest.use_case.di

import com.photoroomtest.use_case.weather.UploadGalleryImageUseCase
import com.photoroomtest.use_case.weather.data.GalleryRepository
import org.koin.dsl.module.module

val useCaseModule = module {
    single { UploadGalleryImageUseCase(get()) }

    single {
        GalleryRepository(
            get()
        )
    }
}
