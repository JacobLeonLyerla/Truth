package com.example.home.impl.model.remote.services

import com.example.home.impl.model.remote.response.PhotoDTO
import retrofit2.http.GET

interface PhotosService {

    @GET("albums/1/photos")
    suspend fun getPhotos(): List<PhotoDTO>
}
