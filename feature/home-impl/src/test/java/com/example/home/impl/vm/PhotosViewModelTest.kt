package com.example.home.impl.vm

import com.example.home.impl.model.remote.repositories.PhotosRepo
import com.example.home.impl.model.testUtil.CoroutinesTestExtension
import com.example.mytruth.core.database.entity.Photo

import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

internal class PhotosViewModelTest {

    @RegisterExtension
    private val coroutinesTestExtension = CoroutinesTestExtension()

    private lateinit var viewModel: PhotosViewModel
    private lateinit var repo: PhotosRepo
    private lateinit var photos: List<Photo>

    @BeforeEach
    fun setup() {
        // Initialize common variables used in multiple tests
        val photo = Photo(
            id = 1,
            albumId = 1,
            title = "title",
            url = "url",
            thumbnailUrl = "thumbUrl"
        )
        photos = listOf(photo)
        repo = mockk()
        viewModel = PhotosViewModel(repo)
    }

    @Test
    fun fetchPhotos() = runTest(coroutinesTestExtension.dispatcher) {
        // Given
        coEvery { repo.photosWithStaleCheck } returns flowOf(photos)

        // When
        viewModel.getPhotosWithStaleCheck()

        // Then
        assertEquals(photos, viewModel.state.photosList)
        assertFalse(viewModel.state.loading)
    }

    @Test
    fun dropTable() = runTest(coroutinesTestExtension.dispatcher) {
        // Given
        coEvery { repo.dropTable() } just Runs

        // When
        viewModel.dropTable()

        // Then
        assertTrue(viewModel.state.photosList.isEmpty())
        assertFalse(viewModel.state.loading)
    }

    @Test
    fun getPhotosSnapshot() = runTest(coroutinesTestExtension.dispatcher) {
        // Given
        coEvery { repo.getPhotosSnapshot() } returns photos

        // When
        viewModel.getPhotosSnapshot()

        // Then
        assertEquals(photos, viewModel.state.photosList)
        assertFalse(viewModel.state.loading)
    }

    @Test
    fun testExceptionHandling() = runTest(coroutinesTestExtension.dispatcher) {
        // Given
        val exceptionMessage = PhotosViewModel.GENERAL_ERROR
        val exception = Exception()
        coEvery { repo.photosWithStaleCheck } throws exception

        // When
        viewModel.getPhotosWithStaleCheck()

        // Then
        assertEquals(exceptionMessage, viewModel.state.error)
        assertFalse(viewModel.state.loading)
    }
    @Test
    fun testNetworkExceptionHandling() = runTest(coroutinesTestExtension.dispatcher) {
        // Given
        val exceptionMessage = PhotosViewModel.NETWORK_ERROR
        val exception = IOException()
        coEvery { repo.photosWithStaleCheck } throws exception

        // When
        viewModel.getPhotosWithStaleCheck()

        // Then
        assertEquals(exceptionMessage, viewModel.state.error)
        assertFalse(viewModel.state.loading)
    }

    @Test
    fun testNotFoundExceptionHandling() = runTest(coroutinesTestExtension.dispatcher) {
        // Given
        val exceptionMessage = PhotosViewModel.NOT_FOUND_ERROR
        val exception = HttpException(Response.error<Any>(404, "".toResponseBody()))
        coEvery { repo.photosWithStaleCheck } throws exception

        // When
        viewModel.getPhotosWithStaleCheck()

        // Then
        assertEquals(exceptionMessage, viewModel.state.error)
        assertFalse(viewModel.state.loading)
    }

    @Test
    fun testServerExceptionHandling() = runTest(coroutinesTestExtension.dispatcher) {
        // Given
        val exceptionMessage = PhotosViewModel.SERVER_ERROR
        val exception = HttpException(Response.error<Any>(500, "".toResponseBody()))
        coEvery { repo.photosWithStaleCheck } throws exception

        // When
        viewModel.getPhotosWithStaleCheck()

        // Then
        assertEquals(exceptionMessage, viewModel.state.error)
        assertFalse(viewModel.state.loading)
    }

    @Test
    fun testAuthorizationExceptionHandling() = runTest(coroutinesTestExtension.dispatcher) {
        // Given
        val exceptionMessage = PhotosViewModel.AUTHORIZATION_ERROR
        val exception401 = HttpException(Response.error<Any>(401, "".toResponseBody()))
        val exception403 = HttpException(Response.error<Any>(403, "".toResponseBody()))
        coEvery { repo.photosWithStaleCheck } throws exception401 andThenThrows exception403

        // When
        viewModel.getPhotosWithStaleCheck()
        viewModel.getPhotosWithStaleCheck() // Call twice to simulate 401 and 403

        // Then
        assertEquals(exceptionMessage, viewModel.state.error)
        assertFalse(viewModel.state.loading)
    }


}
