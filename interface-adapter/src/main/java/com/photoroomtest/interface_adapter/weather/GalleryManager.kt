package com.photoroomtest.interface_adapter.weather

import com.photoroomtest.interface_adapter.weather.model.GalleryImageUiModel
import com.photoroomtest.use_case.weather.UploadGalleryImageUseCase
import io.reactivex.Flowable


class GalleryManager(
    private val uploadGalleryImageUseCase: UploadGalleryImageUseCase,
    private val mapper: GalleryImageUiModel.Mapper
) {
    fun upload(
        b64Image: String
    ): Flowable<GalleryImageUiModel> {
        return uploadGalleryImageUseCase.execute(b64Image).map { galleryImage ->
            mapper.fromEntity(galleryImage)
        }
    }
}
