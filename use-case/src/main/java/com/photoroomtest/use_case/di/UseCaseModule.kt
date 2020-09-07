package com.photoroomtest.use_case.di

import com.photoroomtest.use_case.weather.GetDailyWeatherUseCase
import com.photoroomtest.use_case.weather.GetHourlyWeatherUseCase
import com.photoroomtest.use_case.weather.data.WeatherRepository
import org.koin.dsl.module.module

val useCaseModule = module {
    single { GetDailyWeatherUseCase(get()) }
    single { GetHourlyWeatherUseCase(get()) }

    single {
        WeatherRepository(
            get(),
            get()
        )
    }
}
