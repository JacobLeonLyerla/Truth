package com.example.home.impl.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore

const val TIMESTAMP = "timestamp_preferences"

val Context.timestampDataStore: DataStore<Preferences> by preferencesDataStore(
    name = TIMESTAMP
)