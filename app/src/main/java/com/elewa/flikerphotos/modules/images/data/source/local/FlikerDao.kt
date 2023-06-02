package com.elewa.flikerphotos.modules.images.data.source.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.elewa.flikerphotos.modules.images.data.model.dto.ImageDto

@Dao
interface FlikerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(images: List<ImageDto>)

    @Query("SELECT * FROM image")
    fun pagingSource(): PagingSource<Int, ImageDto>
}