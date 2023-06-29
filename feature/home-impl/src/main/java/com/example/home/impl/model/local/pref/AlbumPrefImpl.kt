package com.example.home.impl.model.local.pref

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

 class AlbumPrefImpl(private val dataStore: DataStore<Preferences>) : AlbumPref {
    private val timestampKey = longPreferencesKey(name = "timestamp")

    override val timestamp: Flow<Long>
        get() = dataStore.data.map { pref -> pref[timestampKey] ?: 0L }

    override suspend fun saveTimestamp(timestamp: Long) {
        dataStore.edit { pref -> pref[timestampKey] = timestamp }
    }

    override suspend fun isDataStale(currentTimestamp: Long): Boolean {
        val lastUpdatedTimestamp = timestamp.first()
        val timePassedSinceLastUpdate = currentTimestamp - lastUpdatedTimestamp
        return timePassedSinceLastUpdate > MAX_AGE
    }

    private companion object {
        private const val MAX_AGE = 60_000L
    }
}
