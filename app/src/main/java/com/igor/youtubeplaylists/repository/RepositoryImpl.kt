package com.igor.youtubeplaylists.repository

import com.igor.youtubeplaylists.modules.YoutubePlaylistsResponse
import com.igor.youtubeplaylists.network.RestApi
import com.igor.youtubeplaylists.network.ResultWrapper
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val restApi: RestApi) : RepositoryController {

    override suspend fun getYoutubePlayList(): ResultWrapper<YoutubePlaylistsResponse?> {
        return try {
            val response = restApi.getAllCities()
            ResultWrapper.Success(response)

        } catch (ex: Exception) {
            ResultWrapper.Error(ex)
        }
    }
}