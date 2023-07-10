package com.example.mytruth.core.preference.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.first

class DefaultPrefImpl(private val dataStore: DataStore<Preferences>) : BasePref {

    override suspend fun <T> save(key: Preferences.Key<T>, value: T) {
        dataStore.edit { preferences ->
            preferences[key] = value
        }
    }

    override suspend fun <T> get(key: Preferences.Key<T>, defaultValue: T): T {
        val preferences = dataStore.data.first()
        return preferences[key] ?: defaultValue
    }

}





