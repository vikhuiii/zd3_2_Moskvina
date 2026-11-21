package com.bignerdranch.android.zd3_2_moskv

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


class MainViewModel : ViewModel() {
    private val apiKey = "2d07c869"

    fun loadMovies(query: String, onSuccess: (List<Movie>) -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.apiService.searchMovies(query, apiKey)
                if (response.Response == "True") {
                    onSuccess(response.Search ?: emptyList())
                } else {
                    onError("Фильмы не найдены")
                }
            } catch (e: Exception) {
                onError("Ошибка сети: ${e.message}")
            }
        }
    }
}