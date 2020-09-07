package com.photoroomtest.gallery.data.remote

import com.photoroomtest.interface_adapter.weather.model.GalleryRemote
import io.reactivex.Flowable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


interface WeatherRetrofitDataSource {

    @POST("/upload")
    @FormUrlEncoded
    fun upload(
        @Field("b64_img") base64: String?
    ): Flowable<GalleryRemote>

}
