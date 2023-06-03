package com.elewa.flikerphotos.core.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.elewa.flikerphotos.modules.images.data.model.dto.ImageDto
import com.elewa.flikerphotos.modules.images.data.model.dto.RemoteKeyDto
import com.elewa.flikerphotos.modules.images.data.source.local.FlikerDao
import com.elewa.flikerphotos.modules.images.data.source.local.RemoteKeyDao

@Database(entities = [ImageDto::class, RemoteKeyDto::class], version = 1, exportSchema = false)
abstract class FlikerDatabase : RoomDatabase() {
    abstract fun flikerDao(): FlikerDao
    abstract fun remoteKeyDao(): RemoteKeyDao
}

const val IMAGE_TABLE = "image"
const val REMOTE_KEY_TABLE = "remote_key"