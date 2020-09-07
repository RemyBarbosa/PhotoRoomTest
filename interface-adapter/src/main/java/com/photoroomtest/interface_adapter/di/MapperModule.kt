package com.photoroomtest.interface_adapter.di

import com.photoroomtest.interface_adapter.weather.model.WeatherRemote
import com.photoroomtest.interface_adapter.weather.model.WeatherUIModel
import org.koin.dsl.module.module

val mapperModule = module {
    single { WeatherUIModel.Mapper() }
    single { WeatherRemote.Mapper() }


}
