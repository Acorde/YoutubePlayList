package com.igor.youtubeplaylists.repository

import com.igor.youtubeplaylists.modules.YoutubePlaylistsResponse
import com.igor.youtubeplaylists.network.RestApi
import com.igor.youtubeplaylists.network.ResultWrapper

class Repository(private val restApi: RestApi) {

    suspend fun getYoutubePlayList(): ResultWrapper<YoutubePlaylistsResponse?> {
        return try {
            val response = restApi.getAllCities()
            ResultWrapper.Success(response)

        } catch (ex: Exception) {
            ResultWrapper.Error(ex)
        }
    }
}