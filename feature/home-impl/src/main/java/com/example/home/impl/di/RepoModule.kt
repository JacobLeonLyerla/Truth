package com.example.home.impl.di

import com.example.mytruth.core.preference.annotation.AlbumDataStore
import com.example.home.impl.annotations.DefaultScope
import com.example.mytruth.core.database.dao.PhotosDao
import com.example.mytruth.core.preference.pref.AlbumPref
import com.example.home.impl.model.remote.repositories.PhotosRepo
import com.example.home.impl.model.remote.repositories.PhotosRepoImpl
import com.example.home.impl.model.remote.services.PhotosService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object RepoModule {

    @Provides
    fun providePhotosService(retrofit: Retrofit): PhotosService {
        return retrofit.create(PhotosService::class.java)
    }

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
