package com.example.movieapp.domain.use_cases

import com.example.movieapp.common.Resource
import com.example.movieapp.domain.model.list.Movie
import com.example.movieapp.domain.repository.GetMovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMovieUseCase @Inject constructor(private val getMovieRepository: GetMovieRepository) {

    operator fun invoke(): Flow<Resource<List<Movie>>> = flow {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(data = getMovieRepository.getMovies()))
        } catch (e : Exception) {
            emit(Resource.Error(message = e.message.toString()))
        }
    }
}