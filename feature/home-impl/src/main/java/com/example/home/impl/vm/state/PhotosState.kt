package com.example.home.impl.vm.state

import com.example.mytruth.core.database.entity.Photo


data class PhotosState(
    val photosList: List<Photo> = emptyList(),
    val loading: Boolean = false,
    val error: String = ""
)
