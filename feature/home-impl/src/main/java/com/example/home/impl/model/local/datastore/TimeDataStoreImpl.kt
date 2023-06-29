package com.example.home.impl.model.local.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import java.io.IOException
import java.time.Instant
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

class TimeDataStoreImpl(
    private val dataStore: DataStore<Preferences>
) : TimeDataStore {


    override suspend fun setCurrentTimestamp() {
        dataStore.edit { preferences ->
            preferences[KEY_NAME] = DateTimeFormatter
                .ofPattern(DATE_PATTERN)
                .withZone(ZoneOffset.UTC)
                .format(Instant.now())
        }
    }

    override suspend fun getCurrentTimestamp(): String? {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                preferences[KEY_NAME]
            }.firstOrNull()
    }

    private companion object {
        val KEY_NAME = stringPreferencesKey(
            name = "timestamp"
        )
        const val DATE_PATTERN = "yyyy-MM-dd HH:mm:ss"
    }
}