package com.igor.youtubeplaylists.network

import com.igor.youtubeplaylists.modules.YoutubePlaylistsResponse
import retrofit2.http.GET

interface RestApi {

    @GET("youtube/playlists.json")
    suspend fun getAllCities(): YoutubePlaylistsResponse
}