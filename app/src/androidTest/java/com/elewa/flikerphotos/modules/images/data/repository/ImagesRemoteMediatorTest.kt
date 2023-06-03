package com.elewa.flikerphotos.modules.images.data.repository

import androidx.paging.*
import com.elewa.flikerphotos.core.data.source.local.FlikerDatabase
import com.elewa.flikerphotos.modules.images.data.mapper.toEntity
import com.elewa.flikerphotos.modules.images.data.model.FakeApiService
import com.elewa.flikerphotos.modules.images.data.model.FakeApiServiceData
import com.elewa.flikerphotos.modules.images.data.model.dto.ImageDto
import com.elewa.flikerphotos.modules.images.data.source.remote.ImagesRemoteMediator
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.IOException
import javax.inject.Inject
import javax.inject.Named

@ExperimentalPagingApi
@OptIn(ExperimentalCoroutinesApi::class)
@HiltAndroidTest
class ImagesRemoteMediatorTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    @Named("fliker_test_db")
    lateinit var database: FlikerDatabase
    @Inject lateinit var apiService: FakeApiService
    @Inject lateinit var fakeApiServiceData: FakeApiServiceData

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @After
    fun tearDown() {
        database.clearAllTables()
        database.close()
    }

    @Test
    fun refreshLoadReturnsSuccessResultWhenMoreDataIsPresent() = runTest {
        val remoteMediator = ImagesRemoteMediator(
            flikerService = apiService,
            flikerDatabase = database
        )
        val pagingState = PagingState<Int,ImageDto> (
            pages = listOf(),
            anchorPosition = null,
            PagingConfig(2),
            leadingPlaceholderCount = 2
        )

        val result = remoteMediator.load(
            LoadType.REFRESH,
            pagingState
        )

        assertTrue((result is RemoteMediator.MediatorResult.Success))
        assertFalse((result as RemoteMediator.MediatorResult.Success).endOfPaginationReached)

    }

    @Test
    fun refreshLoadSuccessAndEndOfPaginationWhenNoMoreData() = runTest {
        // To test endOfPaginationReached, don't set up the mockApi to return post
        // data here.
        val remoteMediator = ImagesRemoteMediator(
            flikerService = apiService,
            flikerDatabase = database
        )
        val pagingState = PagingState<Int,ImageDto> (
            pages = listOf(
                PagingSource.LoadResult.Page(
                    data = fakeApiServiceData.fakedPagedResponses[2].photos.imagesResponse.toEntity(),
                    prevKey = 2,
                    nextKey = fakeApiServiceData.fakedPagedResponses[2].photos.page+1
                )
            ),
            anchorPosition = null,
            PagingConfig(2),
            leadingPlaceholderCount = 2
        )
        remoteMediator.load(LoadType.REFRESH, pagingState)
        remoteMediator.load(LoadType.APPEND, pagingState)
        val result = remoteMediator.load(LoadType.APPEND, pagingState)
        assertTrue((result is RemoteMediator.MediatorResult.Success))
        assertTrue((result as RemoteMediator.MediatorResult.Success).endOfPaginationReached)
    }

    @Test
    fun refreshLoadReturnsErrorResultWhenErrorOccurs() = runTest {
        val error = IOException("Throw test failure")
        apiService.error = error

        val remoteMediator = ImagesRemoteMediator(
            flikerService = apiService,
            flikerDatabase = database
        )
        val pagingState = PagingState<Int, ImageDto>(
            listOf(),
            null,
            PagingConfig(2),
            2
        )
        val result = remoteMediator.load(LoadType.REFRESH, pagingState)
        assertTrue ((result is RemoteMediator.MediatorResult.Error))
    }

}