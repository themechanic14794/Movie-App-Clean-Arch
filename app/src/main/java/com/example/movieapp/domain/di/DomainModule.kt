package com.example.movieapp.domain.di

import com.example.movieapp.data.network.ApiService
import com.example.movieapp.data.repository.GetMovieDetailsRepositoryImpl
import com.example.movieapp.data.repository.GetMoviesRepositoryImpl
import com.example.movieapp.domain.repository.GetMovieDetailsRepository
import com.example.movieapp.domain.repository.GetMovieRepository
import com.example.movieapp.domain.use_cases.GetMovieDetailsUseCase
import com.example.movieapp.domain.use_cases.GetMovieUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object DomainModule {

    @Provides
    fun providesGetMoviesRepository(apiService: ApiService) : GetMovieRepository{
        return GetMoviesRepositoryImpl(apiService)
    }

    @Provides
    fun providesGetMovieDetailsRepository(apiService: ApiService):GetMovieDetailsRepository{
        return GetMovieDetailsRepositoryImpl(apiService)
    }

    @Provides
    fun providesGetMovieUseCase(getMovieRepository: GetMovieRepository) : GetMovieUseCase{
        return GetMovieUseCase(getMovieRepository)
    }

    @Provides
    fun providesGetMovieDetailsUseCase (getMovieDetailsRepository: GetMovieDetailsRepository) : GetMovieDetailsUseCase{
        return GetMovieDetailsUseCase(getMovieDetailsRepository)
    }
}