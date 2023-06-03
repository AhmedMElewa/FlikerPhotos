package com.elewa.flikerphotos.modules.images.di

import androidx.paging.ExperimentalPagingApi
import com.elewa.flikerphotos.modules.images.data.repository.FakeImagesRepositoryImpl
import com.elewa.flikerphotos.modules.images.domain.repository.ImagesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton


@OptIn(ExperimentalPagingApi::class)
@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [ImagesModule::class]
)
abstract class FakeRepositoryModule {

    @Binds
    @Singleton
    abstract fun provideFakeRepository(repo: FakeImagesRepositoryImpl): ImagesRepository

}