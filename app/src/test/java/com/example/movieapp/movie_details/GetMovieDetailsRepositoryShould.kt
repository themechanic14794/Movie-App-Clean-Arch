package com.example.movieapp.movie_details

import com.example.movieapp.BuildConfig
import com.example.movieapp.MainCoroutineRule
import com.example.movieapp.data.model.details.MovieDetailsDTO
import com.example.movieapp.data.network.ApiService
import com.example.movieapp.data.repository.GetMovieDetailsRepositoryImpl
import com.example.movieapp.domain.repository.GetMovieDetailsRepository
import com.example.movieapp.data.mappers.toDomain
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock

@OptIn(ExperimentalCoroutinesApi::class)
class GetMovieDetailsRepositoryShould {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()
    private lateinit var getMovieDetailsRepository: GetMovieDetailsRepository
    private lateinit var apiService: ApiService
    private val movieDetails  = mock<MovieDetailsDTO>()
    private val movieId = "866398"

    @Before
    fun setUp(){
        apiService = mock()
        getMovieDetailsRepository = GetMovieDetailsRepositoryImpl(apiService)
    }

    @Test
    fun returnSuccessWhenGetDataFromBackend() = runTest{
        Mockito.`when`(apiService.getMovieDetails(movieId,BuildConfig.API_KEY)).thenReturn(
            movieDetails
        )
        val result = getMovieDetailsRepository.getMovieDetails(movieId)
        mainCoroutineRule.testDispatcher.scheduler.advanceUntilIdle()
        Assert.assertEquals(movieDetails.toDomain(),result)
    }
}