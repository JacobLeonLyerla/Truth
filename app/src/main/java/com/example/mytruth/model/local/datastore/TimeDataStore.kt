package com.example.mytruth.model.local.datastore

interface TimeDataStore {
    suspend fun setCurrentTimestamp()
    suspend fun getCurrentTimestamp(): String?
}