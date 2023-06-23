package com.example.mytruth.model.remote.services

import com.example.mytruth.model.remote.response.PhotoDTO
import retrofit2.http.GET

interface PhotosService {

    @GET("albums/1/photos")
    suspend fun getPhotos(): List<PhotoDTO>
}
