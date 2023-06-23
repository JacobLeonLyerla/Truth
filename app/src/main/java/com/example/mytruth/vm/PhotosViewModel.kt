package com.example.mytruth.vm

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mytruth.model.remote.repositories.PhotosRepo
import com.example.mytruth.vm.state.PhotosState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class PhotosViewModel @Inject constructor(private val repo: PhotosRepo) : ViewModel() {
    var state by mutableStateOf(PhotosState())
        private set

    private val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        val message = when (exception) {
            is IOException -> NETWORK_ERROR
            is HttpException -> {
                when (exception.code()) {
                    404 -> NOT_FOUND_ERROR
                    500 -> SERVER_ERROR
                    401, 403 -> AUTHORIZATION_ERROR
                    else -> GENERAL_ERROR
                }
            }

            else -> GENERAL_ERROR
        }

        state = state.copy(error = message, loading = false)
    }

    private var currentJob: Job? = null

    fun getPhotosWithStaleCheck() {
        currentJob = viewModelScope.launch(exceptionHandler) {
            state = state.copy(loading = true)
            repo.photosWithStaleCheck.collect { photos ->
                state = state.copy(photosList = photos, loading = false)
            }
        }
    }

    fun dropTable() {
        currentJob = viewModelScope.launch(exceptionHandler) {
            repo.dropTable()
            state = state.copy(photosList = listOf(), loading = false)
        }
    }

    fun getPhotosSnapshot() {
        currentJob = viewModelScope.launch(exceptionHandler) {
            state = state.copy(loading = true)
            val photosSnapshot = repo.getPhotosSnapshot()
            state = state.copy(photosList = photosSnapshot, loading = false)
        }
    }

    companion object {
        const val NETWORK_ERROR = "Network Error. Please check your internet connection."
        const val NOT_FOUND_ERROR = "The data you are looking for could not be found."
        const val SERVER_ERROR = "Our servers are currently down. Please try again later."
        const val AUTHORIZATION_ERROR = "You're not authorized to access this content."
        const val GENERAL_ERROR = "An unexpected error occurred. Please try again later."
    }
}

