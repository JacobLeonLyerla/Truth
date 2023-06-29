package com.example.home.impl.di

import android.content.Context
import androidx.room.Room
import com.example.home.impl.model.local.PhotosDatabase
import com.example.home.impl.model.local.daos.PhotosDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    private const val DATABASE_NAME = "photos_database"

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): PhotosDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            PhotosDatabase::class.java,
            DATABASE_NAME
        ).build()
    }

    @Provides
    fun providePhotosDao(database: PhotosDatabase): PhotosDao {
        return database.photosDao()
    }
}
