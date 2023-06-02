package com.elewa.flikerphotos.modules.images.data.model.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.elewa.flikerphotos.core.data.source.local.IMAGE_TABLE

@Entity(tableName = IMAGE_TABLE)
data class ImageDto(
    @PrimaryKey
    val id: String,
    @ColumnInfo(name = "preview_url")
    val previewUrl: String,
)