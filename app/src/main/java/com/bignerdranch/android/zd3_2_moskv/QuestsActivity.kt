package com.bignerdranch.android.zd3_2_moskv

import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class QuestsActivity : AppCompatActivity() {
    private lateinit var rvChannels: RecyclerView
    private lateinit var rvMovies: RecyclerView
    private lateinit var etSearch: EditText
    private lateinit var movieAdapter: MovieAdapter

    private val channels = listOf("Action", "Comedy", "Drama", "Sci-Fi", "Horror")

    private val stubMovies = listOf(
        Movie("Крепкий орешек", "1988", "tt0095016", "movie", "", "Action"),
        Movie("Матрица", "1999", "tt0133093", "movie", "", "Sci-Fi"),
        Movie("1+1", "2011", "tt1675434", "movie", "", "Drama"),
        Movie("Мальчишник в Вегасе", "2009", "tt1119646", "movie", "", "Comedy"),
        Movie("Сияние", "1980", "tt0081505", "movie", "", "Horror"),
        Movie("Джон Уик", "2014", "tt2911666", "movie", "", "Action"),
        Movie("Интерстеллар", "2014", "tt0816692", "movie", "", "Sci-Fi"),
        Movie("Зеленая миля", "1999", "tt0120689", "movie", "", "Drama")
    )

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
        /*

        val call = RetrofitClient.apiService.searchMovies(query, RetrofitClient.getApiKey())
        call.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.isSuccessful && response.body()?.Response == "True") {
                    val movies = response.body()?.Search ?: emptyList()
                    movieAdapter.updateMovies(movies)
                } else {
                    Toast.makeText(this@QuestsActivity, "Фильмы не найдены", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Toast.makeText(this@QuestsActivity, "Ошибка сети", Toast.LENGTH_SHORT).show()
            }
        })
        */


        val filteredMovies = if (query.isEmpty()) {
            stubMovies
        } else {
            stubMovies.filter {
                it.Genre.equals(query, ignoreCase = true) ||
                        it.Title.contains(query, ignoreCase = true)
            }
        }

        movieAdapter.updateMovies(filteredMovies)

        if (filteredMovies.isEmpty()) {
            Toast.makeText(this, "Фильмы не найдены", Toast.LENGTH_SHORT).show()
        }
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