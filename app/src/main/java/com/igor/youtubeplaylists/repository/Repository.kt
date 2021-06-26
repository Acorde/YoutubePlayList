package com.igor.youtubeplaylists.repository

import android.util.Log
import com.google.gson.Gson
import com.igor.youtubeplaylists.network.RestApi
import com.igor.youtubeplaylists.network.ResultWrapper

class Repository(private val restApi: RestApi) {

    suspend fun getYoutubePlayList() {
        try {
            val response = restApi.getAllCities()
            Log.d("IgorTest", Gson().toJson(response))

        } catch (ex: Exception) {
            ResultWrapper.Error(ex)
        }
    }
}