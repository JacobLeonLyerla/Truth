package com.example.mytruth.annotations

import javax.inject.Qualifier

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class TimeDataStore

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class AlbumDataStore
