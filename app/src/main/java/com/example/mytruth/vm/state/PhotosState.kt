package com.example.mytruth.vm.state

import com.example.mytruth.model.local.entity.Photo

data class PhotosState(
    val photosList: List<Photo> = emptyList(),
    val loading: Boolean = false,
    val error: String = ""
)
