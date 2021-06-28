package com.igor.youtubeplaylists.utils

import android.view.View
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

fun View.showWithView(show: Boolean): Pair<Boolean, View> {
    when (show) {
        true -> this.visibility = View.VISIBLE
        false -> this.visibility = View.GONE
    }
    return Pair(show, this)
}

fun String.parseDateToddMMyyyy(): String? {

    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
    val outputFormat = SimpleDateFormat("dd/MM/yyyy")
    val date: Date
    var str: String? = null
    try {
        date = inputFormat.parse(this)
        str = outputFormat.format(date)
    } catch (e: ParseException) {
        e.printStackTrace()
    }
    return str
}