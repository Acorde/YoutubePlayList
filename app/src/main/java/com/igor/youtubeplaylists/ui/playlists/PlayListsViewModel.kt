package com.igor.youtubeplaylists.ui.playlists

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.igor.youtubeplaylists.modules.YoutubePlaylistsResponse
import com.igor.youtubeplaylists.network.ResultWrapper
import com.igor.youtubeplaylists.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlayListsViewModel @Inject constructor(private val mRepository: Repository) : ViewModel() {

    var playListsData: MutableLiveData<ResultWrapper<YoutubePlaylistsResponse?>> = MutableLiveData()

    fun getPlayLists() {
        viewModelScope.launch {
            val data = mRepository.getYoutubePlayList()
            playListsData.value = data
        }
    }

}
