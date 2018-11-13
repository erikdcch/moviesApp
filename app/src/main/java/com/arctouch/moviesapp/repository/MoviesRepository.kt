package com.arctouch.moviesapp.repository

import com.arctouch.moviesapp.webservice.MoviesResponse
import com.arctouch.moviesapp.webservice.MoviesService
import retrofit2.Call

/**
 * Created by erikcruz on 11/11/18
 */
interface MoviesRepository {
    fun getUpcomingMovies(apiKey: String, page: Int): Call<MoviesResponse>


    class Remote(private val service: MoviesService) : MoviesRepository {

        override fun getUpcomingMovies(apiKey: String, page: Int): Call<MoviesResponse> {
            return service.upcomingMovies(apiKey, page)
        }
    }
}