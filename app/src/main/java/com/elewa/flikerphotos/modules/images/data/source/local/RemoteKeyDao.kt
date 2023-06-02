package com.elewa.flikerphotos.modules.images.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.elewa.flikerphotos.modules.images.data.model.dto.RemoteKeyDto

@Dao
interface RemoteKeyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(remoteKeyDto: RemoteKeyDto)

    @Query("SELECT * FROM remote_key ORDER BY id DESC")
    suspend fun getRemoteKey(): List<RemoteKeyDto>
}