package com.igor.youtubeplaylists.di

import com.igor.youtubeplaylists.network.RestApi
import com.igor.youtubeplaylists.repository.RepositoryController
import com.igor.youtubeplaylists.repository.RepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Singleton
    @Provides
    fun provideRepository(restApi: RestApi): RepositoryController {
        return RepositoryImpl(restApi)
    }
}