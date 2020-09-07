package com.photoroomtest.interface_adapter.weather.model

import com.photoroomtest.entity.weather.GalleryImage

data class GalleryRemote(
    val b64Output: String?
) {

    class Mapper {
        fun toEntity(originalImage : String, galleryRemote: GalleryRemote): GalleryImage {
            return GalleryImage(
                originalImage = originalImage,
                transformedImage = galleryRemote.b64Output ?: ""
            )
        }
    }
}
