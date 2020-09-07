package com.photoroomtest.use_case.weather


import com.photoroomtest.entity.weather.GalleryImage
import com.photoroomtest.use_case.weather.data.GalleryRepository
import io.reactivex.Flowable

class UploadGalleryImageUseCase(
    private val galleryRepository: GalleryRepository
) {
    fun execute(
        b64Image: String
    ): Flowable<GalleryImage> {
        return galleryRepository.upload(b64Image)
    }
}
