package com.photoroomtest.application.di

import com.photoroomtest.gallery.data.remote.WeatherRetrofitDataSource
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val retrofitModule = module {

    single {
        Retrofit.Builder()
                .baseUrl("https://interview.photoroom.com/")
                .addConverterFactory(GsonConverterFactory.create(get()))
                .addCallAdapterFactory(get())
                .client(get())
                .build().create(WeatherRetrofitDataSource::class.java)
    }
}

