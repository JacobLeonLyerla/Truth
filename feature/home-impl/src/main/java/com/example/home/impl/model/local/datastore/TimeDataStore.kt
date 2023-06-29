package com.example.home.impl.model.local.datastore

interface TimeDataStore {
    suspend fun setCurrentTimestamp()
    suspend fun getCurrentTimestamp(): String?
}