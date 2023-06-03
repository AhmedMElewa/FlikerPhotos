package com.elewa.flikerphotos.modules.images.di

import androidx.paging.ExperimentalPagingApi
import com.elewa.flikerphotos.modules.images.data.repository.ImagesRepositoryImpl
import com.elewa.flikerphotos.modules.images.domain.repository.ImagesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@ExperimentalPagingApi
@InstallIn(ViewModelComponent::class)
@Module
abstract class ImagesModule {
    @Binds
    @ViewModelScoped
    abstract fun bindImagesRepository(impl: ImagesRepositoryImpl): ImagesRepository
}