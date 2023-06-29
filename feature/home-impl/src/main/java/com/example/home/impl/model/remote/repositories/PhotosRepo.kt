package com.example.home.impl.model.remote.repositories

import com.example.mytruth.core.database.entity.Photo
import kotlinx.coroutines.flow.Flow

interface PhotosRepo {
    suspend fun getPhotosSnapshot(): List<Photo>
    val photosWithStaleCheck: Flow<List<Photo>>
    suspend fun getPhotos(): List<Photo>
    suspend fun dropTable()
}
