package com.photoroomtest.use_case.weather.data.source

import com.photoroomtest.entity.weather.Weather
import io.reactivex.Flowable

interface WeatherLocalDataSource {
    fun getDailyWeatherList(): Flowable<List<Weather>>
    fun getHourlyWeatherList(): Flowable<List<Weather>>
}
