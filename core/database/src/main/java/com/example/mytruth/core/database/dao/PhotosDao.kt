package com.example.mytruth.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mytruth.core.database.entity.Photo
import kotlinx.coroutines.flow.Flow

@Dao
interface PhotosDao {
    @Insert
    suspend fun insertPhotos(photos: List<Photo>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(photos: List<Photo>)

    @Query("DELETE FROM ${Photo.TABLE_NAME}")
    suspend fun dropTable()

    @Query("SELECT * FROM ${Photo.TABLE_NAME}")
    fun getAllPhotos(): Flow<List<Photo>>
}
