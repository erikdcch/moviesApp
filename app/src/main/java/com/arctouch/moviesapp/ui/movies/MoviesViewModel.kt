package com.arctouch.moviesapp.ui.movies

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.arctouch.moviesapp.model.Movie
import com.arctouch.moviesapp.repository.MoviesRepository
import com.arctouch.moviesapp.utils.API_KEY
import com.arctouch.moviesapp.webservice.MoviesService

class MoviesViewModel : ViewModel() {
    private val service: MoviesService = MoviesService()

    lateinit var movies: MutableLiveData<List<Movie>>

    init {
        loadMovies()
    }

    fun loadMovies() {
        val repository = MoviesRepository.Remote(service)
        movies = repository.getUpcomingMovies(API_KEY, 1)
    }
}
