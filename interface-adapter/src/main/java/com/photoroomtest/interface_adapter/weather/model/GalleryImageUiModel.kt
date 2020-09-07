package com.photoroomtest.interface_adapter.weather.model

import com.photoroomtest.entity.weather.GalleryImage

data class GalleryImageUiModel(
    val originalImage: String,
    val transformedImage: String
) {
    class Mapper {
        fun fromEntity(galleryImage: GalleryImage) =
            GalleryImageUiModel(
                originalImage = galleryImage.originalImage,
                transformedImage = galleryImage.transformedImage
            )
    }
}
