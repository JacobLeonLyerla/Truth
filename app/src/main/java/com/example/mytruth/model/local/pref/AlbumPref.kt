package com.example.mytruth.model.local.pref

import kotlinx.coroutines.flow.Flow

interface AlbumPref {
    val timestamp: Flow<Long>
    suspend fun saveTimestamp(timestamp: Long)
    suspend fun isDataStale(currentTimestamp: Long):Boolean
}
