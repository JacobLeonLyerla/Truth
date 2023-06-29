package com.example.home.impl.di

import com.example.home.impl.annotations.AlbumDataStore
import com.example.home.impl.annotations.DefaultScope
import com.example.home.impl.model.local.daos.PhotosDao
import com.example.home.impl.model.local.pref.AlbumPref
import com.example.home.impl.model.remote.repositories.PhotosRepo
import com.example.home.impl.model.remote.repositories.PhotosRepoImpl
import com.example.home.impl.model.remote.services.PhotosService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope

@Module
@InstallIn(SingletonComponent::class)
object RepoModule {

    @Provides
    fun provideRepository(
        service: PhotosService,
        photoDao: PhotosDao,
        @AlbumDataStore dataStore: AlbumPref,
        @DefaultScope coroutineScope: CoroutineScope
    ): PhotosRepo {
        return PhotosRepoImpl(service, photoDao, dataStore, coroutineScope)
    }
}
