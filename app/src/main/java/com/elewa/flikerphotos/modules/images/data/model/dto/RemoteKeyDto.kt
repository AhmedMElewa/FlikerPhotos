package com.elewa.flikerphotos.modules.images.data.model.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.elewa.flikerphotos.core.data.source.local.REMOTE_KEY_TABLE

@Entity(tableName = REMOTE_KEY_TABLE)
data class RemoteKeyDto(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val page: Int
)