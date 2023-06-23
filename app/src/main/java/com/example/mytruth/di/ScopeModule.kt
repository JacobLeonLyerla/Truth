package com.example.mytruth.di

import com.example.mytruth.annotations.DefaultDispatcher
import com.example.mytruth.annotations.DefaultScope
import com.example.mytruth.annotations.IoDispatcher
import com.example.mytruth.annotations.IoScope
import com.example.mytruth.annotations.MainDispatcher
import com.example.mytruth.annotations.MainScope
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

    @DefaultScope
    @Provides
    fun providesDefaultScope(
        @DefaultDispatcher dispatcher: CoroutineDispatcher
    ): CoroutineScope {
        return CoroutineScope(SupervisorJob() + dispatcher)
    }

    @IoScope
    @Provides
    fun providesIoScope(
        @IoDispatcher dispatcher: CoroutineDispatcher
    ): CoroutineScope {
        return CoroutineScope(SupervisorJob() + dispatcher)
    }

    @MainScope
    @Provides
    fun providesMainScope(
        @MainDispatcher dispatcher: CoroutineDispatcher
    ): CoroutineScope {
        return CoroutineScope(SupervisorJob() + dispatcher)
    }
}