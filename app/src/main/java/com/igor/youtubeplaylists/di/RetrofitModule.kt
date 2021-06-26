package com.igor.youtubeplaylists.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.igor.youtubeplaylists.network.RestApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RetrofitModule {
//https://landing.cal-online.co.il/youtube/playlists.json

    @Singleton
    @Provides
    fun provideGsonBuilder(): Gson {
        return GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
    }

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson): Retrofit.Builder {
        return Retrofit.Builder().baseUrl("https://landing.cal-online.co.il/")
            .addConverterFactory(GsonConverterFactory.create(gson))
    }

    @Singleton
    @Provides
    fun provideRestApi(retrofit: Retrofit.Builder): RestApi {
        return retrofit.build().create(RestApi::class.java)
    }
}