package com.arctouch.moviesapp.ui.movies.details

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.arctouch.moviesapp.model.Movie
import com.arctouch.moviesapp.utils.BASE_IMG_URL

class MovieDetailsViewModel : ViewModel() {
    val name = MutableLiveData<String>()
    val imgUrl = MutableLiveData<String>()
    val releaseDate = MutableLiveData<String>()
    val overview = MutableLiveData<String>()

    fun fillMovieDetails(movie: Movie?) {
        name.value = movie?.name
        imgUrl.value = BASE_IMG_URL + movie?.urlImage
        releaseDate.value = movie?.relaseDateFormat()
        overview.value = movie?.overview
    }

}
