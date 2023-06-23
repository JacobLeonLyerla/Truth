package com.example.mytruth.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.example.mytruth.annotations.AlbumDataStore
import com.example.mytruth.annotations.TimeDataStore
import com.example.mytruth.datastore.albumDataStore
import com.example.mytruth.datastore.timestampDataStore
import com.example.mytruth.model.local.pref.AlbumPref
import com.example.mytruth.model.local.pref.AlbumPrefImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PreferenceModule {

    @TimeDataStore
    @Provides
    @Singleton
    fun provideDataStorePreferences(
        @ApplicationContext applicationContext: Context
    ): DataStore<Preferences> {
        return applicationContext.timestampDataStore
    }

    @AlbumDataStore
    @Provides
    @Singleton
    fun provideAlbumDataStorePreferences(
        @ApplicationContext applicationContext: Context
    ): DataStore<Preferences> {
        return applicationContext.albumDataStore
    }

    @Provides
    @Singleton
    @AlbumDataStore
    fun provideAlbumDataStore(
        @AlbumDataStore dataStore: DataStore<Preferences>
    ): AlbumPref {
        return AlbumPrefImpl(dataStore)
    }
}
