package com.photoroomtest.application.di

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import org.koin.dsl.module.module
import retrofit2.CallAdapter
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

val retrofitDependenciesModule = module {

    single { RxJava2CallAdapterFactory.create() as CallAdapter.Factory }
    single { GsonBuilder().create() as Gson }
    single { OkHttpClient.Builder().addNetworkInterceptor(StethoInterceptor()).build() as OkHttpClient }
}

