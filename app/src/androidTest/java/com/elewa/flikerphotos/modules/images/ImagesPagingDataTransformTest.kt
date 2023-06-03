package com.elewa.flikerphotos.modules.images

import androidx.paging.AsyncPagingDataDiffer
import androidx.paging.PagingData
import androidx.paging.map
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListUpdateCallback
import com.elewa.flikerphotos.modules.images.data.mapper.toEntity
import com.elewa.flikerphotos.modules.images.data.mapper.toDto
import com.elewa.flikerphotos.modules.images.data.model.FakeApiServiceData
import com.elewa.flikerphotos.modules.images.domain.entity.ImageEntity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltAndroidTest
class PagingDataTransformTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)
    private val testScope = TestScope()
    private val dispatcher = StandardTestDispatcher(testScope.testScheduler)

    @Inject lateinit var fakeData: FakeApiServiceData

    @Before
    fun setup() {
        hiltRule.inject()
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun clean() {
        Dispatchers.resetMain()
    }

    @Test
    fun transformImageToUiModel() = testScope.runTest {
        val data  = PagingData.from(fakeData.fakeImagesOne)
        val transformData: PagingData<ImageEntity> = data.map { it.toDto() }.map { it.toEntity() }

        val differ = AsyncPagingDataDiffer(
            diffCallback = ImageDiffCallback(),
            updateCallback = NoopListCallback(),
            workerDispatcher = Dispatchers.Main
        )
        differ.submitData(transformData)
        advanceUntilIdle()
        assertEquals(
            fakeData.fakeImagesOne.map {
                it.toDto().toEntity()
            },
            differ.snapshot().items
        )
    }
}

class ImageDiffCallback : DiffUtil.ItemCallback<ImageEntity>() {
    override fun areItemsTheSame(oldItem: ImageEntity, newItem: ImageEntity): Boolean {
         return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ImageEntity, newItem: ImageEntity): Boolean {
        return oldItem == newItem
    }

}

class NoopListCallback: ListUpdateCallback {
    override fun onChanged(position: Int, count: Int, payload: Any?) {}
    override fun onMoved(fromPosition: Int, toPosition: Int) {}
    override fun onInserted(position: Int, count: Int) {}
    override fun onRemoved(position: Int, count: Int) {}
}