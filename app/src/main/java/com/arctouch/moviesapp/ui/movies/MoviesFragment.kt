package com.arctouch.moviesapp.ui.movies

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arctouch.moviesapp.R
import com.arctouch.moviesapp.model.Movie
import com.arctouch.moviesapp.webservice.Failure
import com.arctouch.moviesapp.webservice.Result
import com.arctouch.moviesapp.webservice.Success
import kotlinx.android.synthetic.main.movies_fragment.*

const val PAGE_START = 1

class MoviesFragment : Fragment() {
    private var listener: OnFragmentInteractionListener? = null
    private var moviesList = mutableListOf<Movie>()
    private var currentPage = PAGE_START
    private lateinit var moviesAdapter: MoviesAdapter

    interface OnFragmentInteractionListener {
        fun goToMovieDetails(movie: Movie)
    }

    companion object {
        fun newInstance() = MoviesFragment()
    }

    private lateinit var viewModel: MoviesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MoviesViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.movies_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupRvMovies()
        viewModel.moviesResponse.observe(this, Observer { renderResult(it!!) })
        viewModel.loadMovies(PAGE_START)
    }

    private fun renderResult(result: Result<List<Movie>>) {
        when (result) {
            is Failure -> showError()
            is Success -> showMovies(result.data)
        }
    }

    private fun setupRvMovies() {
        moviesAdapter = MoviesAdapter(moviesList) { listener?.goToMovieDetails(it) }
        rvMovies.apply {
            layoutManager = GridLayoutManager(context, 3)
            addOnScrollListener(paginationScrollListener)
            adapter = moviesAdapter
        }
    }

    private fun loadMovies(page: Int) {
        progressBar.visibility = View.VISIBLE
        viewModel.loadMovies(page)
    }

    private fun showMovies(movies: List<Movie>) {
        progressBar.visibility = View.GONE
        moviesAdapter.addMovies(movies)
    }

    private fun showError() {
        progressBar.visibility = View.GONE
        Snackbar.make(mainView, R.string.msg_error_connection, Snackbar.LENGTH_INDEFINITE).apply {
            setAction(R.string.refresh) { loadMovies(currentPage) }.show()
        }
    }

    private val paginationScrollListener = object : RecyclerView.OnScrollListener() {

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            if (dy > 0) {
                if (!rvMovies.canScrollVertically(RecyclerView.FOCUS_DOWN)) {
                    currentPage++
                    loadMovies(currentPage)
                }
            }
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
