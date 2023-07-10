package com.example.mytruth.core.preference.annotation

import javax.inject.Qualifier

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class DefaultPrefSecurity

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class SecurePrefSecurity
