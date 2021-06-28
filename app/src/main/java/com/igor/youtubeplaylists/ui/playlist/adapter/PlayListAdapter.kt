package com.igor.youtubeplaylists.ui.playlist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.igor.youtubeplaylists.databinding.ItemPalyListBinding
import com.igor.youtubeplaylists.modules.ItemsItem
import com.igor.youtubeplaylists.utils.showWithView
import javax.inject.Inject

class PlayListAdapter @Inject constructor() :
    RecyclerView.Adapter<PlayListAdapter.ViewHolder>() {

    private var playLists: List<ItemsItem?>? = null
    private var mOnItemClick: ((String) -> Unit)? = null

    private var _binding: ItemPalyListBinding? = null
    private val binding get() = _binding!!

    fun setData(data: List<ItemsItem?>) {
        this.playLists = data
        notifyDataSetChanged()
    }

    fun setOnItemClick(onItemClick: (String) -> Unit) {
        this.mOnItemClick = onItemClick
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        _binding = ItemPalyListBinding.inflate(LayoutInflater.from(parent.context), parent, false)

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
                        item.contentDetails?.videoId?.let { videoId ->
                            if (videoId.isEmpty().not()) {
                                onItemClick.invoke(videoId)
                            }
                        }

                    }
                }
            }
        }
    }


    override fun getItemCount(): Int {
        return playLists?.size ?: 0
    }

    class ViewHolder(private val itemBinding: ItemPalyListBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bindData(data: ItemsItem) {
            setTitle(data.snippet?.title)
            setImage(data.snippet?.thumbnails?.high?.url)

        }

        private fun setImage(url: String?) {
            url?.let {
                Glide.with(itemView.context).load(it).into(itemBinding.videoImage)
            }
        }

        private fun setTitle(title: String?) {
            itemBinding.videoName.showWithView(title?.isEmpty()?.not() == true)
            title?.let { title ->
                itemBinding.videoName.text = title
            }
        }
    }
}