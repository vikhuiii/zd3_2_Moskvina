package com.bignerdranch.android.zd3_2_moskv

import android.app.Activity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class QuestsActivity : AppCompatActivity() {
    private lateinit var rvChannels: RecyclerView
    private lateinit var rvMovies: RecyclerView
    private lateinit var etSearch: EditText
    private lateinit var movieAdapter: MovieAdapter
    private val channels = listOf("Action", "Comedy", "Drama", "Sci-Fi", "Horror")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quests)
        rvChannels = findViewById(R.id.rvChannels)
        rvMovies = findViewById(R.id.rvMovies)
        etSearch = findViewById(R.id.etSearch)

        setupChannels()
        setupMoviesGrid()
        setupSearch()
        loadMovies("Action")
    }
    private fun setupChannels() {
        rvChannels.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvChannels.adapter = ChannelAdapter(channels) { selectedChannel ->
            loadMovies(selectedChannel)
        }
    }
    private fun setupMoviesGrid() {
        rvMovies.layoutManager = GridLayoutManager(this, 4)
        movieAdapter = MovieAdapter(emptyList()) { movie ->
            openMovieDetailFragment(movie.imdbID)
        }
        rvMovies.adapter = movieAdapter
    }
    private fun setupSearch() {
        etSearch.setOnEditorActionListener { _, _, _ ->
            val query = etSearch.text.toString().trim()
            if (query.isNotEmpty()) {
                loadMovies(query)
            } else {
                Toast.makeText(this, "Введите название", Toast.LENGTH_SHORT).show()
            }
            true
        }
    }

    private fun loadMovies(query: String) {
        val call = RetrofitClient.apiService.searchMovies(query, RetrofitClient.getApiKey())

        call.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.isSuccessful && response.body()?.Response == "True") {
                    val movies = response.body()?.Search ?: emptyList()
                    movieAdapter.updateMovies(movies) // Добавь этот метод в свой MovieAdapter
                } else {
                    Toast.makeText(this@QuestsActivity, "Фильмы не найдены", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Toast.makeText(this@QuestsActivity, "Ошибка сети", Toast.LENGTH_SHORT).show()
            }
        })
    }
    private fun openMovieDetailFragment(movieId: String) {
        val fragment = MovieDetailFragment()
        val bundle = Bundle()
        bundle.putString("MOVIE_ID", movieId)
        fragment.arguments = bundle


        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }
}