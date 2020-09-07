package com.photoroomtest.interface_adapter.weather.model

import com.google.gson.annotations.SerializedName

data class GalleryRemoteRequest(
    @SerializedName("b64_img") val originalImage: String?
)
