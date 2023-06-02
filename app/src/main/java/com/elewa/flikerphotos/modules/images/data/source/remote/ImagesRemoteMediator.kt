package com.elewa.flikerphotos.modules.images.data.source.remote

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.LoadType.*
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.elewa.flikerphotos.core.data.source.local.FlikerDatabase
import com.elewa.flikerphotos.modules.images.data.mapper.toEntity
import com.elewa.flikerphotos.modules.images.data.model.dto.ImageDto
import com.elewa.flikerphotos.modules.images.data.model.dto.RemoteKeyDto
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@ExperimentalPagingApi
class ImagesRemoteMediator @Inject constructor(
    private val flikerDatabase: FlikerDatabase,
    private val flikerService: FlikerService
) : RemoteMediator<Int, ImageDto>() {
    private val imagesDao = flikerDatabase.flikerDao()
    private val remoteKeyDao = flikerDatabase.remoteKeyDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, ImageDto>
    ): MediatorResult {
        return try {
            val loadKey = when (loadType) {
                REFRESH -> 1
                PREPEND -> INVALID_PAGE
                APPEND -> {
                    remoteKeyDao.getRemoteKey().firstOrNull()?.page
                }
            }
            var page = loadKey ?: 1
            if (page == INVALID_PAGE) {
                MediatorResult.Success(endOfPaginationReached = true)
            } else {
                val response = flikerService.getImages(page = page)
                val imageList = response.photos.imagesResponse
                if (!imageList.isNullOrEmpty()) {
                    flikerDatabase.withTransaction {
                        remoteKeyDao.insert(RemoteKeyDto(0, page + 1))
                        imagesDao.insertAll(imageList.toEntity())
                    }

                }
                MediatorResult.Success(
                    endOfPaginationReached = false
                )
//                MediatorResult.Success(endOfPaginationReached = page == response.photos.pages || page == null)
            }

        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }

    companion object {
        private const val INVALID_PAGE = -1
    }

}