package com.elewa.flikerphotos.core.di

import android.content.Context
import androidx.room.Room
import com.elewa.flikerphotos.core.data.source.local.FlikerDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Volatile
    private var instance: FlikerDatabase? = null
    private const val DATABASE_NAME = "fliker_db"

    @Singleton
    @Provides
    fun provideDatabaseInstance(@ApplicationContext context: Context): FlikerDatabase {
        return instance ?: synchronized(this) {
            instance ?: buildDatabase(context).also { instance = it }
        }
    }

    private fun buildDatabase(context: Context): FlikerDatabase {
        return Room.databaseBuilder(context, FlikerDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration().build()
    }
}