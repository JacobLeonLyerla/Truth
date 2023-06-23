package com.example.mytruth.di

import com.example.mytruth.annotations.AlbumDataStore
import com.example.mytruth.annotations.DefaultScope
import com.example.mytruth.model.local.daos.PhotosDao
import com.example.mytruth.model.local.pref.AlbumPref
import com.example.mytruth.model.remote.repositories.PhotosRepo
import com.example.mytruth.model.remote.repositories.PhotosRepoImpl
import com.example.mytruth.model.remote.services.PhotosService
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
