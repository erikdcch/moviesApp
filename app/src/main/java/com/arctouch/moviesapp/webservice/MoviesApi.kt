package com.arctouch.moviesapp.webservice

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by erikcruz on 11/11/18
 */
interface MoviesApi {
    companion object {
        private const val PARAM_PAGE = "page"
        private const val PARAM_API = "api_key"
        private const val UPCOMING_MOVIES = "upcoming"
    }

    @GET(UPCOMING_MOVIES)
    fun upcomingMovies(@Query(PARAM_API) apiKey: String, @Query(PARAM_PAGE) page: Int): Call<MoviesResponse>
}