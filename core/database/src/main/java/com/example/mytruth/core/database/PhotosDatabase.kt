package com.example.mytruth.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mytruth.core.database.dao.PhotosDao
import com.example.mytruth.core.database.entity.Photo
import javax.inject.Singleton

@Singleton
@Database(entities = [Photo::class], version = 1)
abstract class PhotosDatabase : RoomDatabase() {
    abstract fun photosDao(): PhotosDao
}
