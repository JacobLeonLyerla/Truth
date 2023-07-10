package com.example.mytruth.core.preference.pref

import androidx.datastore.preferences.core.longPreferencesKey
import com.example.mytruth.core.preference.datastore.BasePref
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

class AlbumPrefImpl(private val dataStore: BasePref) : AlbumPref {
    private val timestampKey = longPreferencesKey(name = "timestamp")

    override val timestamp: Flow<Long>
        get() = flow { emit(dataStore.get(timestampKey, 0L)) }

    override suspend fun saveTimestamp(timestamp: Long) {
        dataStore.save(timestampKey, timestamp)
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
