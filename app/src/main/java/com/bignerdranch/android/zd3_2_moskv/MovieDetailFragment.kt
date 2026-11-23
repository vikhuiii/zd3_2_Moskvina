package com.bignerdranch.android.zd3_2_moskv

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment

class MovieDetailFragment : Fragment() {

    private lateinit var ivPoster: ImageView
    private lateinit var tvTitle: TextView
    private lateinit var tvInfo: TextView
    private lateinit var tvPlot: TextView
    private lateinit var btnBack: Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movie_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ivPoster = view.findViewById(R.id.ivPoster)
        tvTitle = view.findViewById(R.id.tvTitle)
        tvInfo = view.findViewById(R.id.tvInfo)
        tvPlot = view.findViewById(R.id.tvPlot)
        btnBack = view.findViewById(R.id.btnBack)

        val movieId = arguments?.getString("MOVIE_ID")
        if (movieId != null) {
            loadMovieDetails(movieId)
        }

        btnBack.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

        btnBack.requestFocus()
    }

    private fun loadMovieDetails(imdbId: String) {
        /*

        val call = RetrofitClient.apiService.searchMovies(imdbId, RetrofitClient.getApiKey())
        call.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: retrofit2.Call<MovieResponse>, response: Response<MovieResponse>) {
                // ... старый код ...
            }
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Toast.makeText(context, "Ошибка загрузки деталей", Toast.LENGTH_SHORT).show()
            }
        })
        */


        tvTitle.text = "Название фильма (ID: $imdbId)"
        tvInfo.text = "Год: 2024 | Жанр: Фантастика / Боевик"
        tvPlot.text = "Это демонстрационная заглушка описания фильма. Здесь должен быть подробный сюжет, но сейчас мы показываем, как работает интерфейс без реальных запросов к API. Фильтрация по жанрам работает отлично!"


        ivPoster.setImageResource(R.drawable.kazan)
    }
}