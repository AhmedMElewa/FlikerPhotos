package com.elewa.flikerphotos.modules.images.data.source.remote

import com.elewa.flikerphotos.modules.images.data.model.api.MainResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface FlikerService {
    @GET("services/rest/")
    suspend fun getImages(
        @Query("method") method: String= "flickr.photos.search",
        @Query("format") format: String="json",
        @Query("nojsoncallback") noJsonCallback: Int=50,
        @Query("text") text: String="Color",
        @Query("page") page: Int,
        @Query("per_page") perPage: Int = 20,
    ): MainResponse
}