package com.example.home.impl.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.example.mytruth.core.preference.annotation.AlbumDataStore
import com.example.mytruth.core.preference.annotation.TimeDataStore
import com.example.home.impl.datastore.albumDataStore
import com.example.home.impl.datastore.timestampDataStore
import com.example.mytruth.core.preference.annotation.DefaultPrefSecurity
import com.example.mytruth.core.preference.annotation.SecurePrefSecurity
import com.example.mytruth.core.preference.datastore.BasePref
import com.example.mytruth.core.preference.datastore.DefaultPrefImpl
import com.example.mytruth.core.preference.datastore.SecurePrefImpl
import com.example.mytruth.core.preference.pref.AlbumPref
import com.example.mytruth.core.preference.pref.AlbumPrefImpl

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
    @DefaultPrefSecurity
    fun provideDefaultPref(
        @AlbumDataStore dataStore: DataStore<Preferences>
    ): BasePref {
        return DefaultPrefImpl(dataStore)
    }

    @Provides
    @Singleton
    @SecurePrefSecurity
    fun provideSecurePref(
        @AlbumDataStore dataStore: DataStore<Preferences>
    ): BasePref {
        return SecurePrefImpl(dataStore)
    }

    @Provides
    @Singleton
    @AlbumDataStore
    fun provideAlbumDataStore(
      @DefaultPrefSecurity  basePref: BasePref
    ): AlbumPref {
        return AlbumPrefImpl(basePref)
    }
}

