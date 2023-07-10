package com.example.mytruth.core.preference.datastore

import androidx.datastore.preferences.core.Preferences

interface BasePref {
    suspend fun <T> save(key: Preferences.Key<T>, value: T)
    suspend fun <T> get(key: Preferences.Key<T>, defaultValue: T): T
}