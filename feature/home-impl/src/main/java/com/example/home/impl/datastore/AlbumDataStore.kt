package com.example.home.impl.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore

const val PREF_NAME = "album_preferences"

val Context.albumDataStore: DataStore<Preferences> by preferencesDataStore(name = PREF_NAME)
