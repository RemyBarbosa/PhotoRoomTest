package com.photoroomtest.use_case.weather.data.source

import com.photoroomtest.entity.weather.GalleryImage
import io.reactivex.Flowable

interface WeatherLocalDataSource {
    fun getDailyWeatherList(): Flowable<List<GalleryImage>>
    fun getHourlyWeatherList(): Flowable<List<GalleryImage>>
}
