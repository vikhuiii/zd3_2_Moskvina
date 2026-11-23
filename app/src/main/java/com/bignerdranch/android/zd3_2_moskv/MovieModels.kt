package com.bignerdranch.android.zd3_2_moskv

data class MovieResponse(
    val Search: List<Movie>?,
    val Response: String
)

data class Movie(
    val Title: String,
    val Year: String,
    val imdbID: String,
    val Type: String,
    val Poster: String,
    val Genre: String = ""
)

data class MovieDetail(
    val Title: String,
    val Year: String,
    val Rated: String,
    val Released: String,
    val Runtime: String,
    val Genre: String,
    val Director: String,
    val Actors: String,
    val Plot: String,
    val Poster: String,
    val Response: String
)