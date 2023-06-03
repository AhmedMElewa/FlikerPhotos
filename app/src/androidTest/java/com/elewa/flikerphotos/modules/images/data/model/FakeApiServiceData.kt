package com.elewa.flikerphotos.modules.images.data.model

import com.elewa.flikerphotos.modules.images.data.model.api.ImageResponse
import com.elewa.flikerphotos.modules.images.data.model.api.MainResponse
import com.elewa.flikerphotos.modules.images.data.model.api.PhotosResponse


class FakeApiServiceData {

    val fakeImagesOne = listOf(
        ImageResponse(
            id = "1",
            owner = "123",
            secret = "111",
            server = "123",
            farm = "123",
            title = "123",
            isPublic = 1,
            isFriend = 0,
            isFamily = 0,
        ),
        ImageResponse(
            id = "2",
            owner = "123",
            secret = "123",
            server = "123",
            farm = "123",
            title = "123",
            isPublic = 1,
            isFriend = 0,
            isFamily = 0,
        )
    )
    private val fakeImagesTwo = listOf(
        ImageResponse(
            id = "3",
            owner = "123",
            secret = "111",
            server = "123",
            farm = "123",
            title = "123",
            isPublic = 1,
            isFriend = 0,
            isFamily = 0,
        ),
        ImageResponse(
            id = "4",
            owner = "123",
            secret = "123",
            server = "123",
            farm = "123",
            title = "123",
            isPublic = 1,
            isFriend = 0,
            isFamily = 0,
        )
    )
    private val fakeImagesThree = listOf(
        ImageResponse(
            id = "5",
            owner = "123",
            secret = "111",
            server = "123",
            farm = "123",
            title = "123",
            isPublic = 1,
            isFriend = 0,
            isFamily = 0,
        ),
        ImageResponse(
            id = "6",
            owner = "123",
            secret = "123",
            server = "123",
            farm = "123",
            title = "123",
            isPublic = 1,
            isFriend = 0,
            isFamily = 0,
        )
    )

    private val fakePhotosResponse = listOf(
        PhotosResponse(
            page = 1,
            pages = 3,
            perPage = 2,
            total = 7,
            imagesResponse = fakeImagesOne
        ),
        PhotosResponse(
            page = 2,
            pages = 3,
            perPage = 2,
            total = 7,
            imagesResponse = fakeImagesTwo
        ),
        PhotosResponse(
            page = 3,
            pages = 3,
            perPage = 2,
            total = 7,
            imagesResponse = fakeImagesThree
        ),
    )

    val fakedPagedResponses: List<MainResponse>
        get() = listOf(
            MainResponse(photos = fakePhotosResponse[0], stat = "Ok"),
            MainResponse(photos = fakePhotosResponse[1], stat = "Ok"),
            MainResponse(photos = fakePhotosResponse[2], stat = "Ok"),
        )


}