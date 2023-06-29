package com.example.home.impl.vm.state

import com.example.home.impl.model.local.entity.Photo

data class PhotosState(
    val photosList: List<Photo> = emptyList(),
    val loading: Boolean = false,
    val error: String = ""
)
