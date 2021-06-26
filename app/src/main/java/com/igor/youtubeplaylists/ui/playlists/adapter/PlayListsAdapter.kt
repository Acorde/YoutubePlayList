package com.igor.youtubeplaylists.ui.playlists.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.igor.youtubeplaylists.R
import com.igor.youtubeplaylists.modules.ItemsItem
import com.igor.youtubeplaylists.utils.showWithView
import kotlinx.android.synthetic.main.item_paly_lists.view.*
import javax.inject.Inject

class PlayListsAdapter @Inject constructor() :
    RecyclerView.Adapter<PlayListsAdapter.ViewHolder>() {

    private var playLists: List<ItemsItem?>? = null
    private var mOnItemClick: ((ItemsItem) -> Unit)? = null


    fun setData(data: List<ItemsItem?>) {
        this.playLists = data
        notifyDataSetChanged()
    }

    fun setOnItemClick(onItemClick: (ItemsItem) -> Unit) {
        this.mOnItemClick = onItemClick
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return LayoutInflater.from(parent.context).inflate(R.layout.item_paly_lists, parent, false)
            .let {
                ViewHolder(it)
            }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        playLists?.let { list ->
            list[position]?.let {
                holder.bindData(it)
            }
        }
    }


    override fun getItemCount(): Int {
        return playLists?.size ?: 0
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindData(data: ItemsItem) {
            setTitle(data.snippet?.title)
            setDate(data.snippet?.publishedAt)

        }

        private fun setDate(publishedAt: String?) {
            itemView.item_playlists_date.showWithView(publishedAt?.isEmpty()?.not() == true)
            publishedAt?.let { title ->
                itemView.item_playlists_date.text = title
            }
        }

        private fun setTitle(title: String?) {
            itemView.item_playlists_name.showWithView(title?.isEmpty()?.not() == true)
            title?.let { title ->
                itemView.item_playlists_name.text = title
            }
        }
    }
}