package com.arctouch.moviesapp.ui.movies

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.arctouch.moviesapp.R
import com.arctouch.moviesapp.model.Movie
import com.arctouch.moviesapp.ui.movies.details.MovieDetailsFragment

class MoviesActivity : AppCompatActivity(), MoviesFragment.OnFragmentInteractionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .add(R.id.container, MoviesFragment.newInstance())
                    .commit()
        }
    }

    override fun goToMovieDetails(movie: Movie) {
        changeFragment(MovieDetailsFragment.newInstance(movie))
    }

    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .addToBackStack(fragment.javaClass.name)
                .commit()
    }
}
