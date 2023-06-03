package com.elewa.flikerphotos.modules.images.data.model.api

import com.google.gson.annotations.SerializedName

data class MainResponse(
    @SerializedName("photos")
    val photos: PhotosResponse,
    @SerializedName("stat")
    val stat: String
)

data class PhotosResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("pages")
    val pages: Int,
    @SerializedName("perpage")
    val perPage: Int,
    @SerializedName("total")
    val total: Int,
    @SerializedName("photo")
    val imagesResponse: List<ImageResponse>
)