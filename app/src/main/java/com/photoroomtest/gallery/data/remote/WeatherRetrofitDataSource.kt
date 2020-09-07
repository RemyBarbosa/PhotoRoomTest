package com.photoroomtest.gallery.data.remote

import com.photoroomtest.interface_adapter.weather.model.GalleryRemote
import com.photoroomtest.interface_adapter.weather.model.GalleryRemoteRequest
import io.reactivex.Flowable
import retrofit2.http.Body
import retrofit2.http.POST


interface WeatherRetrofitDataSource {

    @POST("/upload")
    fun upload(@Body galleryRemoteRequest: GalleryRemoteRequest): Flowable<GalleryRemote>
}
