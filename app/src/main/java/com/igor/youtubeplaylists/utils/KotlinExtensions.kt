package com.igor.youtubeplaylists.utils

import android.view.View

fun View.showWithView(show: Boolean): Pair<Boolean, View> {
    when (show) {
        true -> this.visibility = View.VISIBLE
        false -> this.visibility = View.GONE
    }
    return Pair(show, this)
}