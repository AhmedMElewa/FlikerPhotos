package com.elewa.flikerphotos.modules.images.data.repository

import android.util.Log
import androidx.paging.*
import com.elewa.flikerphotos.core.data.source.local.FlikerDatabase
import com.elewa.flikerphotos.modules.images.data.mapper.toEntity
import com.elewa.flikerphotos.modules.images.data.source.remote.FlikerService
import com.elewa.flikerphotos.modules.images.data.source.remote.ImagesRemoteMediator
import com.elewa.flikerphotos.modules.images.domain.entity.ImageEntity
import com.elewa.flikerphotos.modules.images.domain.repository.ImagesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import timber.log.Timber
import javax.inject.Inject
import kotlin.math.log

@ExperimentalPagingApi
class ImagesRepositoryImpl
@Inject constructor(
    private val flikerDatabase: FlikerDatabase,
    private val flikerService: FlikerService
) :
    ImagesRepository {
    private val imagesDao = flikerDatabase.flikerDao()
    override fun getImages(): Flow<PagingData<ImageEntity>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            remoteMediator = ImagesRemoteMediator(flikerDatabase, flikerService),
            pagingSourceFactory = {
                imagesDao.pagingSource()
            }
        ).flow.map { pagingData ->
            pagingData.map { imageDto ->
                imageDto.toEntity()
            }
        }.catch {
            Timber.e("Elewa", it.message.toString())
        }.flowOn(Dispatchers.IO)

    }

}