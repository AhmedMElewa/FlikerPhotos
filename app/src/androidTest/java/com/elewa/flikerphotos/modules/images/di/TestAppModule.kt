package com.elewa.flikerphotos.modules.images.di

import android.content.Context
import androidx.room.Room
import com.elewa.flikerphotos.core.data.source.local.FlikerDatabase
import com.elewa.flikerphotos.modules.images.data.model.FakeApiService
import com.elewa.flikerphotos.modules.images.data.model.FakeApiServiceData
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object TestAppModule {

    @Singleton
    @Provides
    @Named("fliker_test_db")
    fun provideFlikerInMemoryDb(@ApplicationContext context: Context): FlikerDatabase {
        return Room.inMemoryDatabaseBuilder(
            context,
            FlikerDatabase::class.java
        ).allowMainThreadQueries().build()
    }

    @Singleton
    @Provides
    fun provideApiFakeServiceData() = FakeApiServiceData()

    @Singleton
    @Provides
    fun provideApiService(fakeData: FakeApiServiceData): FakeApiService {
        return FakeApiService().apply {
            addImage(1,fakeData.fakedPagedResponses[0])
            addImage(2,fakeData.fakedPagedResponses[1])
            addImage(1,fakeData.fakedPagedResponses[2])
        }
    }
}