package com.photoroomtest.use_case.weather.data.source

import com.photoroomtest.entity.weather.GalleryImage
import io.reactivex.Flowable

interface GalleryRemoteDataSource {
    fun upload(
        b64Image: String
    ): Flowable<GalleryImage>
}
