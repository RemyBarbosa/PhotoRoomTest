package com.photoroomtest.gallery.data.remote

import com.photoroomtest.entity.weather.GalleryImage
import com.photoroomtest.interface_adapter.weather.model.GalleryRemote
import com.photoroomtest.use_case.weather.data.source.GalleryRemoteDataSource
import io.reactivex.Flowable

class GalleryRemoteDataSourceImpl(
    private val mapper: GalleryRemote.Mapper,
    private val weatherRetrofitDataSource: WeatherRetrofitDataSource
) : GalleryRemoteDataSource {

    override fun upload(b64Image: String): Flowable<GalleryImage> {
        return weatherRetrofitDataSource.upload(b64Image).map { transformedImage ->
            mapper.toEntity(b64Image, transformedImage)
        }
    }
}
