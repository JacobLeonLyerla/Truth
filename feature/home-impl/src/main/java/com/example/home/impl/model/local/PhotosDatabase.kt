package com.example.home.impl.model.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.home.impl.model.local.daos.PhotosDao
import com.example.home.impl.model.local.entity.Photo
import javax.inject.Singleton

@Singleton
@Database(entities = [Photo::class], version = 1)
abstract class PhotosDatabase : RoomDatabase() {
    abstract fun photosDao(): PhotosDao
}
