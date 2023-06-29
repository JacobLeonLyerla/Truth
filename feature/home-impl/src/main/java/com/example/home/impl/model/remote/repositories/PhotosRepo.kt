package com.example.home.impl.model.remote.repositories

import com.example.home.impl.model.local.entity.Photo
import kotlinx.coroutines.flow.Flow

interface PhotosRepo {
    suspend fun getPhotosSnapshot(): List<Photo>
    val photosWithStaleCheck: Flow<List<Photo>>
    suspend fun getPhotos(): List<Photo>
    suspend fun dropTable()
}
