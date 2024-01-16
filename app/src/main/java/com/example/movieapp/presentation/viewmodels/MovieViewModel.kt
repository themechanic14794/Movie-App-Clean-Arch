package com.example.movieapp.presentation.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.common.Resource
import com.example.movieapp.domain.use_cases.GetMovieUseCase
import com.example.movieapp.presentation.stateholders.MovieStateHolder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val movieUseCase: GetMovieUseCase): ViewModel() {

    val movieListStateHolder = mutableStateOf(MovieStateHolder())

    init {
        getMovieList()
    }

    private fun getMovieList() = viewModelScope.launch {

        movieUseCase().onEach {
                when(it){
                    is Resource.Loading -> {
                        movieListStateHolder.value = MovieStateHolder(isLoading = true)
                    }
                    is Resource.Success -> {
                        movieListStateHolder.value = MovieStateHolder(data = it.data)
                    }
                    is Resource.Error -> {
                        movieListStateHolder.value = MovieStateHolder(error= it.message.toString())
                    }

                }
            }.launchIn(viewModelScope)

        }
    }