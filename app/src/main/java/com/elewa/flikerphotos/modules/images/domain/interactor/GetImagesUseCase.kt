package com.elewa.flikerphotos.modules.images.domain.interactor

import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.elewa.flikerphotos.base.BaseUseCase
import com.elewa.flikerphotos.modules.images.domain.entity.ImageEntity
import com.elewa.flikerphotos.modules.images.domain.repository.ImagesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class GetImagesUseCase @Inject constructor(private val repository: ImagesRepository) :
    BaseUseCase<Nothing, Flow<PagingData<ImageEntity>>> {

    override suspend fun execute(params: Nothing?): Flow<PagingData<ImageEntity>> {
        return repository.getImages()
    }
}