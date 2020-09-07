package com.photoroomtest.use_case.weather.data

import com.photoroomtest.entity.weather.GalleryImage
import com.photoroomtest.use_case.weather.data.source.GalleryRemoteDataSource
import io.reactivex.Flowable

class GalleryRepository(
    private val galleryRemoteDataSource: GalleryRemoteDataSource
) {
    fun upload(b64Image: String): Flowable<GalleryImage> =
        galleryRemoteDataSource.upload(b64Image)
}
