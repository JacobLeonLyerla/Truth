package com.example.home.impl.model.remote.response

import com.example.mytruth.core.database.entity.Photo
import com.squareup.moshi.Json

data class PhotoDTO(
    @Json(name = "albumId") val albumId: Int,
    @Json(name = "id") val id: Int,
    @Json(name = "thumbnailUrl") val thumbnailUrl: String,
    @Json(name = "title") val title: String,
    @Json(name = "url") val url: String
) {
    //todo move this logic into an extension util that we have in the impl
    fun toPhotoItem(): Photo {
        return Photo(
            albumId = this.albumId,
            id = this.id,
            thumbnailUrl = this.thumbnailUrl,
            title = this.title,
            url = this.url
        )
    }
}

