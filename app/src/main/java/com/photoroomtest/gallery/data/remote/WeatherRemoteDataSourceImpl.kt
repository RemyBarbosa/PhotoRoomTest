package com.photoroomtest.gallery.data.remote

import com.photoroomtest.entity.weather.Weather
import com.photoroomtest.interface_adapter.weather.model.WeatherRemote
import com.photoroomtest.use_case.weather.data.source.WeatherRemoteDataSource
import io.reactivex.Flowable

class WeatherRemoteDataSourceImpl(
    private val mapper: WeatherRemote.Mapper,
    private val weatherRetrofitDataSource: WeatherRetrofitDataSource
) : WeatherRemoteDataSource {

    override fun getDailyWeatherList(latitude: Float, longitude: Float, count: Int, units: String, appId: String): Flowable<List<Weather>> {
        return  weatherRetrofitDataSource.getDailyWeatherList(latitude, longitude, count, units, appId).map { dailyWeatherRemoteList ->
            dailyWeatherRemoteList.list.map {
                mapper.toEntity(it)
            }
        }
    }

    override fun getHourlyWeatherList(latitude: Float, longitude: Float, count: Int, units: String, appId: String): Flowable<List<Weather>> {
        return  weatherRetrofitDataSource.getHourlyWeatherList(latitude, longitude, count, units, appId).map { dailyWeatherRemoteList ->
            dailyWeatherRemoteList.list.map {
                mapper.toEntity(it)
            }
        }
    }
}
