package com.elewa.flikerphotos.modules.images.data.model


import com.elewa.flikerphotos.modules.images.data.model.api.MainResponse
import com.elewa.flikerphotos.modules.images.data.model.api.PhotosResponse
import com.elewa.flikerphotos.modules.images.data.source.remote.FlikerService
import java.io.IOException

class FakeApiService : FlikerService {

    private val model = mutableMapOf<Int, MainResponse>()

    var error: IOException? = null


    fun addImage(key: Int,pagedResponse: MainResponse) {
        model.putIfAbsent(key,pagedResponse)
    }

    override suspend fun getImages(
        method: String,
        format: String,
        noJsonCallback: Int,
        text: String,
        page: Int,
        perPage: Int
    ): MainResponse {
        error?.let {
            throw it
        }
        return model.getOrDefault(
            page, MainResponse(
                photos = PhotosResponse(
                    page = 1,
                    pages = 3,
                    perPage = 2,
                    total = 7,
                    imagesResponse = emptyList()
                ),
                stat = "Ok"

            )
        )
    }
}