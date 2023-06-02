package com.elewa.flikerphotos.modules.images.presentation.model

import androidx.paging.PagingData
import com.elewa.flikerphotos.modules.images.domain.entity.ImageEntity


data class ImagesState(
    val isLoading: Boolean = false,
    val data: PagingData<ImageEntity>? = null,
    val error: Throwable? = null
)