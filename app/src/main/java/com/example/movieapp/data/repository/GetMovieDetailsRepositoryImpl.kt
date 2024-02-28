package com.example.movieapp.data.repository

import com.example.movieapp.BuildConfig
import com.example.movieapp.data.network.ApiService
import com.example.movieapp.domain.model.details.MovieDetails
import com.example.movieapp.domain.repository.GetMovieDetailsRepository
import com.example.movieapp.data.remote.mappers.toDomain
import javax.inject.Inject

class GetMovieDetailsRepositoryImpl @Inject constructor(private val apiService : ApiService) : GetMovieDetailsRepository {

    override suspend fun getMovieDetails(id: String): MovieDetails {
        return apiService.getMovieDetails(id = id, apiKey = BuildConfig.API_KEY).toDomain()
    }
}