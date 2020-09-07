package com.photoroomtest.use_case.weather


import com.photoroomtest.entity.weather.Weather
import com.photoroomtest.use_case.weather.data.WeatherRepository
import io.reactivex.Flowable

class GetHourlyWeatherUseCase(
    private val weatherRepository: WeatherRepository
) {
    fun execute(
        latitude: Float,
        longitude: Float,
        count: Int,
        units: String,
        appId: String
    ): Flowable<List<Weather>> {
        return weatherRepository.getHourlyWeatherList(latitude, longitude, count, units, appId)
    }
}
