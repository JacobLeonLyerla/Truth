package com.example.home.impl.model.remote.repositories

import com.example.mytruth.core.database.dao.PhotosDao
import com.example.mytruth.core.preference.pref.AlbumPref
import com.example.home.impl.model.remote.response.PhotoDTO
import com.example.home.impl.model.remote.services.PhotosService
import com.example.home.impl.model.testUtil.CoroutinesTestExtension
import com.example.mytruth.core.database.entity.Photo
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension

internal class PhotosRepoImplTest {
    // Define properties for mocked objects and extensions
    @RegisterExtension
    private val coroutinesTestExtension = CoroutinesTestExtension()
    private val photosDAO: PhotosDao = mockk {
        every { getAllPhotos() } answers { photoFlow }
        coEvery { dropTable() } just Runs
    }
    private val albumPref: AlbumPref = mockk {
        coEvery { saveTimestamp(any()) } coAnswers { }
        coEvery { isDataStale(any()) } coAnswers { true }
    }
    private val photosService: PhotosService = mockk{
        coEvery { getPhotos() } coAnswers { photoDTOs }
    }
    private val scope: CoroutineScope = TestScope(coroutinesTestExtension.dispatcher)

    // PhotoItem and Photos related variables
    private lateinit var photoDTOs: ArrayList<PhotoDTO>
    private lateinit var photos: List<Photo>
    private lateinit var updates: MutableList<List<Photo>>
    private lateinit var photoFlow: MutableStateFlow<List<Photo>>

    // Initialize the class under test with mocked objects
    private val repo = PhotosRepoImpl(
        service = photosService,
        photosDao = photosDAO,
        dataStore = albumPref,
        repoScope = scope,
    )

    @BeforeEach
    fun setup() {
        // Initialize common variables used in multiple tests
        val photoDTO = PhotoDTO(
            id = 1,
            albumId = 1,
            title = "title",
            url = "url",
            thumbnailUrl = "thumbUrl"
        )

        // We add the DTO object to a list of DTOs and convert the list to a list of domain objects
        photoDTOs = ArrayList<PhotoDTO>().apply { add(photoDTO) } // Update the type to ArrayList
        photos = photoDTOs.map { it.toPhotoItem() }
        updates = mutableListOf()
        photoFlow = MutableStateFlow(emptyList<Photo>())


        // Instructs that the method just runs without any specific behaviour
        coEvery { photosDAO.insertAll(photos) } coAnswers { photoFlow.emit(photos) }


    }

    // Define the test case
    @Test
    @DisplayName("Testing photosWithStaleCheck when data is empty")
    fun fetchPhotos() = runTest(coroutinesTestExtension.dispatcher) {
        // Set up mock behaviours specific to this test
        coEvery { albumPref.isDataStale(any()) } coAnswers { false }

        // When
        val job = launch { repo.photosWithStaleCheck.toList(updates) }

        // Then
        Assertions.assertEquals(2, updates.size)

        // Finally, we cancel the job to prevent it from running indefinitely
        job.cancel()
    }

    @Test
    @DisplayName("Testing photosWithStaleCheck when data is stale")
    fun fetchPhotosWhenDataIsStale() = runTest(coroutinesTestExtension.dispatcher) {
        // When
        val job = launch { repo.photosWithStaleCheck.toList(updates) }

        // Then
        Assertions.assertEquals(2, updates.size)

        // Finally
        job.cancel()
    }

    @Test
    @DisplayName("Testing photosWithStaleCheck when data is empty and stale")
    fun fetchPhotosWhenDataIsEmptyAndIsStale() = runTest(coroutinesTestExtension.dispatcher) {
        // Set up mock behaviours specific to this test
        photoDTOs = ArrayList() // Empty list for this test
        photos = photoDTOs.map { it.toPhotoItem() }
        coEvery { photosDAO.insertAll(photos) } coAnswers { photoFlow.emit(photos) }

        // When
        val job = launch { repo.photosWithStaleCheck.toList(updates) }

        // Then
        Assertions.assertEquals(1, updates.size)

        // Finally
        job.cancel()
    }

    @Test
    @DisplayName("Testing getPhotosSnapshot when data is empty")
    fun testGetPhotosSnapshotEmpty() = runTest(coroutinesTestExtension.dispatcher) {
        // Given
        every { photosDAO.getAllPhotos() } returns flowOf(emptyList())

        // When
        val snapshot = repo.getPhotosSnapshot()

        // Then
        Assertions.assertTrue(snapshot.isEmpty())
    }

    @Test
    @DisplayName("Testing getPhotosSnapshot when data is not empty")
    fun testGetPhotosSnapshotNotEmpty() = runTest(coroutinesTestExtension.dispatcher) {
        // Mock DAO to return the list of photos
        every { photosDAO.getAllPhotos() } returns flowOf(photos)

        // When
        val snapshot = repo.getPhotosSnapshot()

        // Then
        Assertions.assertEquals(photos, snapshot)
    }

    @Test
    @DisplayName("Testing getPhotos")
    fun testGetPhotos() = runTest(coroutinesTestExtension.dispatcher) {

        // Given
        coEvery { photosService.getPhotos() } returns photoDTOs

        // When
        val fetchedPhotos = repo.getPhotos()

        // Then
        Assertions.assertEquals(photos, fetchedPhotos)
    }

    @Test
    @DisplayName("Testing dropTable ")
    fun testDropTable() = runTest(coroutinesTestExtension.dispatcher) {
        // Given
        coEvery { photosDAO.dropTable() } coAnswers {
            photoFlow.emit(emptyList())
        }
        every { photosDAO.getAllPhotos() } returns photoFlow // this ensures the flow returns the updated value after dropTable

        // When
        repo.dropTable()

        // Then
        coVerify { photosDAO.dropTable() }

        // Check that the flow is now empty after the drop table operation
        Assertions.assertTrue(photoFlow.value.isEmpty())
    }


    @Test
    @DisplayName("Testing dropTable is called")
    fun testDropTableIsCalled() = runTest(coroutinesTestExtension.dispatcher) {
        // When
        repo.dropTable()

        // Then
        coVerify { photosDAO.dropTable() }
    }
}
