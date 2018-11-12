package com.arctouch.moviesapp.ui.movies.details

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arctouch.moviesapp.R
import com.arctouch.moviesapp.model.Movie
import com.arctouch.moviesapp.utils.BASE_IMG_URL
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.movie_details_fragment.*

private const val ARG_MOVIE = "ARG_MOVIE"

class MovieDetailsFragment : Fragment() {
    private var movie: Movie? = null
    private lateinit var viewModel: MovieDetailsViewModel

    companion object {
        fun newInstance(movie: Movie) =
                MovieDetailsFragment().apply {
                    arguments = Bundle().apply {
                        putParcelable(ARG_MOVIE, movie)
                    }
                }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            movie = it.getParcelable(ARG_MOVIE)
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.movie_details_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MovieDetailsViewModel::class.java)

        showInfo()
    }

    private fun showInfo() {
        tvName.text = movie?.name
        tvReleaseDate.text = movie?.relaaseDateFormat()
        tvOverview.text = movie?.overview
        if (movie?.urlImage!!.isNotEmpty()) Glide.with(this).load(BASE_IMG_URL + movie?.urlImage).into(ivPoster)
    }

}
