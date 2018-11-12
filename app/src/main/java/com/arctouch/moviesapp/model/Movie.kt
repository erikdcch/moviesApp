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
        val name: String,
        val urlImage: String,
        val genre: String,
        val releaseDate: Date,
        val overview: String) : Parcelable {

    fun relaaseDateFormat(): String {
        val format = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault())
        return format.format(releaseDate)
    }
}