package com.arctouch.moviesapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by erikcruz on 11/9/18
 */
@Parcelize
data class Movie(
    var name: String ?= "",
    var urlImage: String ?= "",
    var genre: String ?= "",
    var releaseDate: Date,
    var overview: String ?= "") : Parcelable {

    fun relaseDateFormat(): String {
        val format = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault())
        return format.format(releaseDate)
    }
}