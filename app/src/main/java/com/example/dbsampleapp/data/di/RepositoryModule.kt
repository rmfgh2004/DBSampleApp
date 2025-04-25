package com.example.dbsampleapp.data.di

import com.example.dbsampleapp.data.dao.ContentDao
import com.example.dbsampleapp.repository.ContentRepository
import com.example.dbsampleapp.repository.ContentRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    @ViewModelScoped
    fun providesContentRepository(contentDao: ContentDao) : ContentRepository = ContentRepositoryImpl(contentDao)
}