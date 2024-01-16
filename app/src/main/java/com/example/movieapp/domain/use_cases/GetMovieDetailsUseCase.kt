package com.example.movieapp.domain.use_cases

import com.example.movieapp.common.Resource
import com.example.movieapp.domain.model.details.MovieDetails
import com.example.movieapp.domain.repository.GetMovieDetailsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(private val getMovieDetailsRepository: GetMovieDetailsRepository) {

    operator fun invoke(id:String) : Flow<Resource<MovieDetails>> = flow {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(data = getMovieDetailsRepository.getMovieDetails(id = id)))
        } catch (e : Exception) {
            emit(Resource.Error(message = e.message.toString()))
        }
    }

}