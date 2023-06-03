package com.elewa.flikerphotos.modules.images.domain.repository

import androidx.paging.PagingData
import com.elewa.flikerphotos.modules.images.domain.entity.ImageEntity
import kotlinx.coroutines.flow.Flow

interface ImagesRepository {
    fun getImages(): Flow<PagingData<ImageEntity>>
}