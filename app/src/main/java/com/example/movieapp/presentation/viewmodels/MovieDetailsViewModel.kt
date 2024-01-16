package com.example.movieapp.presentation.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.common.Resource
import com.example.movieapp.domain.use_cases.GetMovieDetailsUseCase
import com.example.movieapp.presentation.stateholders.MovieDetailsStateHolder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(private val movieDetailsUseCase: GetMovieDetailsUseCase,savedStateHandle: SavedStateHandle) : ViewModel() {

    val movieDetailsStateHolder = mutableStateOf(MovieDetailsStateHolder())

    init {
        viewModelScope.launch {

            savedStateHandle.getStateFlow("id", "0").collectLatest {

                getMovieDetails(it)

            }
        }
    }

    private fun getMovieDetails(id: String) = viewModelScope.launch {

        movieDetailsUseCase(id).onEach {
            when(it){

                is Resource.Loading -> {
                    movieDetailsStateHolder.value = MovieDetailsStateHolder(isLoading = true)
                }

                is Resource.Success -> {
                    movieDetailsStateHolder.value = MovieDetailsStateHolder(data = it.data)
                }

                is Resource.Error -> {
                    movieDetailsStateHolder.value = MovieDetailsStateHolder(error = it.message.toString())
                }
            }
        }.launchIn(viewModelScope)
    }
}