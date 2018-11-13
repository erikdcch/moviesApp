package com.arctouch.moviesapp.ui.movies

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.arctouch.moviesapp.model.Movie
import com.arctouch.moviesapp.repository.MoviesRepository
import com.arctouch.moviesapp.utils.API_KEY
import com.arctouch.moviesapp.webservice.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesViewModel : ViewModel() {
    private val service: MoviesService = MoviesService()
    private var repository: MoviesRepository
    var moviesResponse: MutableLiveData<Result<List<Movie>>> = MutableLiveData()

    init {
        repository = MoviesRepository.Remote(service)
    }

    fun loadMovies(page: Int) {
        repository.getUpcomingMovies(API_KEY, page).enqueue(object : Callback<MoviesResponse> {
            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                moviesResponse.postValue(Failure(t))
            }

            override fun onResponse(call: Call<MoviesResponse>, response: Response<MoviesResponse>) {
                if (response.isSuccessful) {
                    moviesResponse.postValue(Success(response.body()?.results?.map { it.toMovie() }!!))
                } else {
                    moviesResponse.postValue(Failure(Throwable()))
                }
            }
        })
    }

}
