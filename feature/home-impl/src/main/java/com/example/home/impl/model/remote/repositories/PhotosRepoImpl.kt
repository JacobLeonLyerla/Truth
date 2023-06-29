package com.example.home.impl.model.remote.repositories

import com.example.mytruth.core.database.dao.PhotosDao
import com.example.mytruth.core.database.entity.Photo
import com.example.home.impl.model.local.pref.AlbumPref
import com.example.home.impl.model.remote.services.PhotosService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.withContext

class PhotosRepoImpl(
    private val service: PhotosService,
    private val photosDao: PhotosDao,
    private val dataStore: AlbumPref,
    private val repoScope: CoroutineScope,
) : PhotosRepo {

    override suspend fun getPhotosSnapshot(): List<Photo> {
        return withContext(repoScope.coroutineContext) {
            photosDao.getAllPhotos().firstOrNull() ?: emptyList()
        }
    }


    override val photosWithStaleCheck: Flow<List<Photo>>
        get() = photosDao.getAllPhotos().onEach { photos ->
            val isDatastale = dataStore.isDataStale((System.currentTimeMillis()))
            if (photos.isEmpty() || isDatastale) {
                val freshFetchedPhotos = getPhotos()
                withContext(repoScope.coroutineContext) { savePhotos(freshFetchedPhotos) }
            }
        }

    private suspend fun savePhotos(freshFetchedPhotos: List<Photo>) {
        photosDao.insertAll(freshFetchedPhotos)
        dataStore.saveTimestamp(System.currentTimeMillis())
    }

    override suspend fun getPhotos(): List<Photo> {
        return withContext(repoScope.coroutineContext) {
            val response = service.getPhotos()
            response.map { it.toPhotoItem() }
        }
    }

    override suspend fun dropTable() {
        withContext(repoScope.coroutineContext) {
            photosDao.dropTable()
        }
    }
}
