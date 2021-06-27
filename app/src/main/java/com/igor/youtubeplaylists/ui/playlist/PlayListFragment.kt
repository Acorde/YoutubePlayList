package com.igor.youtubeplaylists.ui.playlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.igor.youtubeplaylists.databinding.FragmentPlayListBinding
import com.igor.youtubeplaylists.modules.ItemsItem
import com.igor.youtubeplaylists.modules.PlaylistItems
import com.igor.youtubeplaylists.ui.playlist.adapter.PlayListAdapter
import com.igor.youtubeplaylists.utils.showWithView
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PlayListFragment : Fragment() {
    private val args: PlayListFragmentArgs by navArgs()
    private val viewModel: PlayListViewModel by viewModels()

    private var _binding: FragmentPlayListBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var adapter: PlayListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPlayListBinding.inflate(inflater, container, false)
        return _binding!!.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()
        getExtraData { playlistItems ->
            playlistItems?.let {
                setRecyclerViewData(it.items)
                binding.playListProgressBar.showWithView(false)
            }
        }
    }


    private fun getExtraData(listener: (PlaylistItems?) -> Unit) {
        listener.invoke(args.extraSelectedItemsItem)
    }


    private fun setRecyclerViewData(playLists: List<ItemsItem?>?) {
        playLists?.let {
            adapter.setData(it)
        }

    }

    private fun setRecyclerView() {
        context?.let { context ->
            binding.playListRv.layoutManager =
                StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            binding.playListRv.adapter = adapter
            adapter.setOnItemClick {
                viewModel.playVideo(it)
            }
        }
    }
}