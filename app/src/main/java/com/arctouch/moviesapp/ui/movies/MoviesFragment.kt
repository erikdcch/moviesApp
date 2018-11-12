package com.arctouch.moviesapp.ui.movies

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arctouch.moviesapp.R
import com.arctouch.moviesapp.model.Movie
import kotlinx.android.synthetic.main.movies_fragment.*


class MoviesFragment : Fragment() {
    private var listener: OnFragmentInteractionListener? = null

    interface OnFragmentInteractionListener {
        fun goToMovieDetails(movie: Movie)
    }

    companion object {
        fun newInstance() = MoviesFragment()
    }

    private lateinit var viewModel: MoviesViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.movies_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MoviesViewModel::class.java)
        viewModel.movies.observe(this, Observer { showMovies(it) })

        setupRvMovies()
        loadMovies()
    }


    private fun setupRvMovies() {
        rvMovies.layoutManager = GridLayoutManager(context, 3)
    }

    private fun loadMovies() {
        progressBar.visibility = View.VISIBLE
        viewModel.loadMovies()
    }

    private fun showMovies(movies: List<Movie>?) {
        progressBar.visibility = View.GONE
        rvMovies.adapter = MoviesAdapter(movies.orEmpty()) {
            listener?.goToMovieDetails(it)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MoviesFragment.OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

}
