package com.bignerdranch.android.zd3_2_moskv

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MovieAdapter(
    private var movies: List<Movie>,
    private val onItemClick: (Movie) -> Unit
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    inner class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivPoster: ImageView = view.findViewById(R.id.ivMoviePoster)
        val tvTitle: TextView = view.findViewById(R.id.tvMovieTitle)
        val tvYear: TextView = view.findViewById(R.id.tvMovieYear)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.tvTitle.text = movie.Title
        holder.tvYear.text = movie.Year

        if (movie.Poster != "N/A") {
            Glide.with(holder.itemView.context)
                .load(movie.Poster)
                .into(holder.ivPoster)
        }

        holder.itemView.setOnClickListener { onItemClick(movie) }

        holder.itemView.setOnFocusChangeListener { v, hasFocus ->
            v.scaleX = if (hasFocus) 1.1f else 1.0f
            v.scaleY = if (hasFocus) 1.1f else 1.0f
        }
    }

    override fun getItemCount() = movies.size

    fun updateMovies(newMovies: List<Movie>) {
        this.movies = newMovies
        notifyDataSetChanged()
    }
}