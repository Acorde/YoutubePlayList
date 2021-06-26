package com.igor.youtubeplaylists.network

import okhttp3.ResponseBody
import retrofit2.http.GET

interface RestApi {

    @GET("youtube/playlists.json")
    suspend fun getAllCities(): ResponseBody
}