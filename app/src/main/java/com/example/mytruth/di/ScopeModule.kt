package com.example.mytruth.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

@Module
@InstallIn(SingletonComponent::class)
object ScopeModule {

    @com.example.home.impl.annotations.DefaultScope
    @Provides
    fun providesDefaultScope(
        @com.example.home.impl.annotations.DefaultDispatcher dispatcher: CoroutineDispatcher
    ): CoroutineScope {
        return CoroutineScope(SupervisorJob() + dispatcher)
    }

    @com.example.home.impl.annotations.IoScope
    @Provides
    fun providesIoScope(
        @com.example.home.impl.annotations.IoDispatcher dispatcher: CoroutineDispatcher
    ): CoroutineScope {
        return CoroutineScope(SupervisorJob() + dispatcher)
    }

    @com.example.home.impl.annotations.MainScope
    @Provides
    fun providesMainScope(
        @com.example.home.impl.annotations.MainDispatcher dispatcher: CoroutineDispatcher
    ): CoroutineScope {
        return CoroutineScope(SupervisorJob() + dispatcher)
    }
}