package com.example.mytruth.model.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mytruth.model.local.daos.PhotosDao
import com.example.mytruth.model.local.entity.Photo
import javax.inject.Singleton

@Singleton
@Database(entities = [Photo::class], version = 1)
abstract class PhotosDatabase : RoomDatabase() {
    abstract fun photosDao(): PhotosDao
}
