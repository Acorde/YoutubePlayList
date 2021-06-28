package com.igor.youtubeplaylists.repository

import com.igor.youtubeplaylists.modules.YoutubePlaylistsResponse
import com.igor.youtubeplaylists.network.ResultWrapper

interface RepositoryController {
    suspend fun getYoutubePlayList(): ResultWrapper<YoutubePlaylistsResponse?>
}