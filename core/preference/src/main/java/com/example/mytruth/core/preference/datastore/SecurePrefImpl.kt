package com.example.mytruth.core.preference.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.example.mytruth.core.preference.util.AESUtil
import kotlinx.coroutines.flow.first

class SecurePrefImpl(private val dataStore: DataStore<Preferences>) : BasePref {

    override suspend fun <T> save(key: Preferences.Key<T>, value: T) {
        val encryptedValue = encrypt.encrypt("$value", SECRET_KEY)
        println(encryptedValue)
        dataStore.edit { preferences ->
            preferences[key] = value
        }
    }

    override suspend fun <T> get(key: Preferences.Key<T>, defaultValue: T): T {
        val preferences = dataStore.data.first()
        return preferences[key] ?: defaultValue
    }

    private companion object {
        const val SECRET_KEY = "Very-secure-very-secret"
        private val encrypt = AESUtil
    }

}

// (private val dataStore: DataStore<Preferences>) : BasePref {
//
//    override suspend fun <T> save(key: Preferences.Key<T>, value: T) {
//        if (value is String) {
//            val encryptedValue = encrypt.encrypt(value, SECRET_KEY)
//            dataStore.edit { preferences ->
//                @Suppress("UNCHECKED_CAST")
//                preferences[key as Preferences.Key<String>] = encryptedValue
//            }
//        } else {
//            throw IllegalArgumentException("SecurePrefImpl only supports String values.")
//        }
//    }
//
//    override suspend fun <T> get(key: Preferences.Key<T>, defaultValue: T): T {
//        val preferences = dataStore.data.first()
//
//        @Suppress("UNCHECKED_CAST")
//        val encryptedValue = preferences[key as Preferences.Key<String>]
//
//        return if (encryptedValue != null) {
//            @Suppress("UNCHECKED_CAST")
//            encrypt.decrypt(encryptedValue, SECRET_KEY) as T
//        } else {
//            defaultValue
//        }
//    }
//
//    private companion object {
//        const val SECRET_KEY = "Very-secure-very-secret"
//        private val encrypt = AESUtil
//    }
//
//}

