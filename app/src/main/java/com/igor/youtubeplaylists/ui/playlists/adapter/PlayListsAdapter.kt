package com.igor.youtubeplaylists.ui.playlists.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.igor.youtubeplaylists.R
import com.igor.youtubeplaylists.databinding.ItemPalyListsBinding
import com.igor.youtubeplaylists.modules.ItemsItem
import com.igor.youtubeplaylists.modules.PlaylistItems
import com.igor.youtubeplaylists.utils.parseDateToddMMyyyy
import com.igor.youtubeplaylists.utils.showWithView
import javax.inject.Inject

class PlayListsAdapter @Inject constructor() :
    RecyclerView.Adapter<PlayListsAdapter.ViewHolder>() {

    private var playLists: List<ItemsItem?>? = null
    private var mOnItemClick: ((PlaylistItems) -> Unit)? = null

    private var _binding: ItemPalyListsBinding? = null
    private val binding get() = _binding!!

    fun setData(data: List<ItemsItem?>) {
        this.playLists = data
        notifyDataSetChanged()
    }

    fun setOnItemClick(onItemClick: (PlaylistItems) -> Unit) {
        this.mOnItemClick = onItemClick
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        _binding = ItemPalyListsBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return _binding!!.root
            .let {
                ViewHolder(binding)
            }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        playLists?.let { list ->
            list[position]?.let { item ->
                holder.bindData(item)
                holder.itemView.setOnClickListener {
                    mOnItemClick?.let { onItemClick ->
                        item.playlistItems?.let {
                            onItemClick.invoke(item.playlistItems)
                        }
                    }
                }
            }
        }
    }


    override fun getItemCount(): Int {
        return playLists?.size ?: 0
    }

    class ViewHolder(private val itemBinding: ItemPalyListsBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bindData(data: ItemsItem) {
            setTitle(data.snippet?.title)
            setDate(data.snippet?.publishedAt)

        }

        private fun setDate(publishedAt: String?) {
            itemBinding.itemPlaylistsDate.showWithView(publishedAt?.isEmpty()?.not() == true).let {

            }
            publishedAt?.let { date ->
                itemBinding.itemPlaylistsDate.text =
                    itemView.context.getString(R.string.published_date, date.parseDateToddMMyyyy())
            }
        }

        private fun setTitle(title: String?) {
            itemBinding.itemPlaylistsName.showWithView(title?.isEmpty()?.not() == true)
            title?.let { title ->
                itemBinding.itemPlaylistsName.text = title
            }
        }
    }
}