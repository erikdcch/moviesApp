package com.arctouch.moviesapp.repository

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.arctouch.moviesapp.model.Movie
import com.arctouch.moviesapp.webservice.MoviesResponse
import com.arctouch.moviesapp.webservice.MoviesService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by erikcruz on 11/11/18
 */
interface MoviesRepository {
    fun getUpcomingMovies(apiKey: String, page: Int): MutableLiveData<List<Movie>>


    class Remote(private val service: MoviesService) : MoviesRepository {

        override fun getUpcomingMovies(apiKey: String, page: Int): MutableLiveData<List<Movie>> {
            val movieLiveData = MutableLiveData<List<Movie>>()

            service.upcomingMovies(apiKey, page).enqueue(object : Callback<MoviesResponse> {
                override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                    Log.d("Error", t.message)
                }

                override fun onResponse(call: Call<MoviesResponse>, response: Response<MoviesResponse>) {
                    if (response.isSuccessful) {
                        movieLiveData.postValue(response.body()?.results?.map { it.toMovie() })
                    }
                }
            })

            return movieLiveData
        }
    }
}