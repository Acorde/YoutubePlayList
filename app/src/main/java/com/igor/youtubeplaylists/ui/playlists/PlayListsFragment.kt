package com.igor.youtubeplaylists.ui.playlists

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.igor.youtubeplaylists.R
import com.igor.youtubeplaylists.databinding.FragmentPlayListsBinding
import com.igor.youtubeplaylists.modules.ItemsItem
import com.igor.youtubeplaylists.modules.YoutubePlaylistsResponse
import com.igor.youtubeplaylists.network.ResultWrapper
import com.igor.youtubeplaylists.ui.playlists.adapter.PlayListsAdapter
import com.igor.youtubeplaylists.utils.showWithView
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PlayListsFragment : Fragment() {

    @Inject
    lateinit var adapter: PlayListsAdapter

    private var _binding: FragmentPlayListsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: PlayListsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPlayListsBinding.inflate(inflater, container, false)

        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()
        subscribeObservers()
        getPlayLists()

    }

    private fun getPlayLists() {
        binding.playListsProgressBar.showWithView(true)
        viewModel.getPlayLists()
    }

    private fun setRecyclerView() {
        context?.let { context ->
            binding.playListsRv.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            binding.playListsRv.adapter = adapter

            adapter.setOnItemClick { selectedPlayList ->
                // viewModel.onPlaylistItemClicked(it)
                activity?.let {
                    val navHostFragment =
                        it.supportFragmentManager.findFragmentById(R.id.main_container) as NavHostFragment
                    navHostFragment.navController.let { navController ->
                        navController.navigate(
                            PlayListsFragmentDirections.actionPlayListsFragmentToPlayListFragment(
                                selectedPlayList
                            )
                        )
                    }
                }
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
            binding.playListsProgressBar.showWithView(false)
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

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}