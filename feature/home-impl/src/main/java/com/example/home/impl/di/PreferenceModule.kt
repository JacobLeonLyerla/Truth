package com.example.home.impl.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.example.home.impl.annotations.AlbumDataStore
import com.example.home.impl.datastore.albumDataStore
import com.example.home.impl.datastore.timestampDataStore
import com.example.home.impl.model.local.pref.AlbumPref
import com.example.home.impl.model.local.pref.AlbumPrefImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PreferenceModule {

    @com.example.home.impl.annotations.TimeDataStore
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
