package com.arctouch.moviesapp.webservice

import com.arctouch.moviesapp.model.Movie
import java.util.*

/**
 * Created by erikcruz on 11/11/18
 */
data class MoviesResponse(
        val results: List<MovieResponse>
)

data class MovieResponse(
        private val vote_count: Int,
        private val id: String,
        private val video: Boolean,
        private val vote_average: Float,
        private val title: String,
        private val popularity: Double,
        private val poster_path: String,
        private val original_language: String,
        private val original_title: String,
        private val genre_ids: List<Int>,
        private val backdrop_path: String,
        private val adult: Boolean,
        private val overview: String,
        private val release_date: Date) {

    fun toMovie() = Movie(title, poster_path, genre_ids.toString(), release_date, overview)

}