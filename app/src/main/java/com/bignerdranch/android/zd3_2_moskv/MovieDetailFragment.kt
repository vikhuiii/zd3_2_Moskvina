package com.bignerdranch.android.zd3_2_moskv

import android.os.Bundle
import android.telecom.Call
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import kotlinx.coroutines.*
import javax.security.auth.callback.Callback


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
        tvPlot = view.findViewById(R.tvPlot)
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
       val call = RetrofitClient.apiService.searchMovies(imdbId, RetrofitClient.getApiKey())

        call.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: retrofit2.Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.isSuccessful && response.body()?.Response == "True") {
                    val movie = response.body()?.Search?.firstOrNull()
                    movie?.let {
                        tvTitle.text = it.Title
                        tvInfo.text = "Год: ${it.Year}"
                        tvPlot.text = "Описание недоступно в кратком поиске. ID: ${it.imdbID}"
                        if (it.Poster != "N/A") {
                            Picasso.get().load(it.Poster).into(ivPoster)
                        }
                    }
                }
            }
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Toast.makeText(context, "Ошибка загрузки деталей", Toast.LENGTH_SHORT).show()
            }
        })
    }
}