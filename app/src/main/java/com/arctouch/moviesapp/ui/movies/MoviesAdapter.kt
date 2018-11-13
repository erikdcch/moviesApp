package com.arctouch.moviesapp.ui.movies

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.arctouch.moviesapp.R
import com.arctouch.moviesapp.model.Movie
import com.arctouch.moviesapp.utils.BASE_IMG_URL
import com.arctouch.moviesapp.utils.extensions.inflate
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.row_movie.view.*

/**
 * Created by erikcruz on 11/9/18
 */
class MoviesAdapter(private val movies: MutableList<Movie>, private val listener: (Movie) -> Unit) :
        RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int) = MovieViewHolder(parent.inflate(R.layout.row_movie))

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(viewHolder: MovieViewHolder, position: Int) =
            viewHolder.bind(movies[position], listener)

    class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: Movie, listener: (Movie) -> Unit) = with(itemView) {
            Glide.with(this).load(BASE_IMG_URL + item.urlImage).into(ivMovie)
            tvTitle.text = item.name
            setOnClickListener { listener(item) }
        }
    }

    fun addMovies(list: List<Movie>) {
        movies.addAll(list)
        this.notifyDataSetChanged()
    }
}