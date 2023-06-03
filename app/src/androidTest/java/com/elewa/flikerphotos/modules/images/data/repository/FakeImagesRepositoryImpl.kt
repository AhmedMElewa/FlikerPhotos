package com.elewa.flikerphotos.modules.images.data.repository

import androidx.paging.PagingData
import com.elewa.flikerphotos.modules.images.data.mapper.toEntity
import com.elewa.flikerphotos.modules.images.data.mapper.toDto
import com.elewa.flikerphotos.modules.images.data.model.api.ImageResponse
import com.elewa.flikerphotos.modules.images.domain.entity.ImageEntity
import com.elewa.flikerphotos.modules.images.domain.repository.ImagesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class FakeImagesRepositoryImpl @Inject constructor() : ImagesRepository {

    private val imageList: List<ImageResponse> = listOf(
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
        ),
    )

    override fun getImages(): Flow<PagingData<ImageEntity>> {
        val dtoList = imageList.toDto()
        val entityList = dtoList.map { it.toEntity() }
        return flowOf(PagingData.from(
            entityList
        ))
    }
}