package com.example.movieapp.presentation.movies.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.movieapp.domain.use_cases.GetMovieDetailsUseCase
import javax.inject.Inject

class MovieImageViewModel@Inject constructor(private val movieDetailsUseCase: GetMovieDetailsUseCase, savedStateHandle: SavedStateHandle) : ViewModel() {

}