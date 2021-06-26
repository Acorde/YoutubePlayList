package com.igor.youtubeplaylists.ui.playlists

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.igor.youtubeplaylists.R
import com.igor.youtubeplaylists.modules.ItemsItem
import com.igor.youtubeplaylists.modules.YoutubePlaylistsResponse
import com.igor.youtubeplaylists.network.ResultWrapper
import com.igor.youtubeplaylists.ui.playlists.adapter.PlayListsAdapter
import com.igor.youtubeplaylists.utils.showWithView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_play_lists.*
import javax.inject.Inject

@AndroidEntryPoint
class PlayListsFragment : Fragment() {

    @Inject
    lateinit var adapter: PlayListsAdapter

    private val viewModel: PlayListsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_play_lists, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()
        subscribeObservers()
        getPlayLists()

    }

    private fun getPlayLists() {
        play_lists_progress_bar.showWithView(true)
        viewModel.getPlayLists()
    }

    private fun setRecyclerView() {
        context?.let { context ->
            play_lists_rv.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            play_lists_rv.adapter = adapter

            adapter.setOnItemClick {
                //TODO... handle onClick
            }
        }
    }

    private fun setRecyclerViewData(playLists: List<ItemsItem?>?) {
        playLists?.let {
            adapter.setData(it)
        }

    }

    private fun subscribeObservers() {
        viewModel.playListsData.observe(viewLifecycleOwner, { result ->
            play_lists_progress_bar.showWithView(false)
            when (result) {
                is ResultWrapper.Success -> setRecyclerViewData((result.data as YoutubePlaylistsResponse).items)
                is ResultWrapper.Error -> view?.let {
                    Snackbar.make(
                        it,
                        result.exception.message ?: "Error",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            }

        })
    }


}