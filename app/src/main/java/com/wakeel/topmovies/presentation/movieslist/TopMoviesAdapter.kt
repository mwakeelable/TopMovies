package com.wakeel.topmovies.presentation.movieslist

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import com.wakeel.topmovies.R
import com.wakeel.topmovies.domain.MoviesViewModel
import kotlinx.android.synthetic.main.movie_list_item.view.*

class TopMoviesAdapter constructor(
    private val onMovieSelected:
        (MoviesViewModel, View) -> Unit
) :
    RecyclerView.Adapter<TopMoviesAdapter.MovieCellViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieCellViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.movie_list_item,
            parent,
            false
        )
        return MovieCellViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieCellViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie, onMovieSelected)
    }

    private val movies: MutableList<MoviesViewModel> = mutableListOf()


    override fun getItemCount(): Int {
        return movies.size
    }


    fun addMovies(movies: List<MoviesViewModel>) {
        this.movies.addAll(movies)
        notifyDataSetChanged()
    }

    class MovieCellViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(
            movie: MoviesViewModel,
            listener: (MoviesViewModel, View) -> Unit
        ) = with(itemView) {
            title.text = movie.title
            movie.posterPath?.let { Picasso.with(context).load("https://image.tmdb.org/t/p/w342" + it).into(image) }
            setOnClickListener { listener(movie, itemView) }
        }

    }
}