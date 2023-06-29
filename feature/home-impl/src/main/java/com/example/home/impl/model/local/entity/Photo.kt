package com.example.home.impl.model.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = Photo.TABLE_NAME)
data class Photo(
    val albumId: Int = 0,
    @PrimaryKey
    val id: Int = 0,
    val thumbnailUrl: String = "",
    val title: String = "",
    val url: String = ""
) {
    companion object {
        const val TABLE_NAME = "photos"
    }
}
