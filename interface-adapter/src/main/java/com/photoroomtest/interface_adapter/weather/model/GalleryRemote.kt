package com.photoroomtest.interface_adapter.weather.model

import com.google.gson.annotations.SerializedName
import com.photoroomtest.entity.weather.GalleryImage

data class GalleryRemote(
    @SerializedName("b64_output")  val transformedImage: String?
) {

    class Mapper {
        fun toEntity(originalImage : String, galleryRemote: GalleryRemote): GalleryImage {
            return GalleryImage(
                originalImage = originalImage,
                transformedImage = galleryRemote.transformedImage ?: ""
            )
        }
    }
}
