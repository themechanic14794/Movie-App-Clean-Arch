package com.example.movieapp.movie_list

import com.example.movieapp.domain.model.list.Movie
import com.example.movieapp.domain.repository.GetMovieRepository
import com.example.movieapp.domain.use_cases.GetMovieUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

@OptIn(ExperimentalCoroutinesApi::class)
class GetMovieUseCaseShould {


    private lateinit var getMovieRepository: GetMovieRepository
    private lateinit var getMovieUseCase: GetMovieUseCase
    private val movieList  = mock <List<Movie>>()

    @Before
    fun setUp(){
        getMovieRepository = mock()
        getMovieUseCase = GetMovieUseCase(getMovieRepository)

    }

    @Test
    fun returnMovieFromUseCaseInSuccess() = runTest{
        `when`(getMovieRepository.getMovies()).thenReturn(
            movieList
        )
        getMovieUseCase.invoke().onEach {
            Assert.assertEquals(movieList,it.data)
        }
    }

    @Test
    fun returnErrorFromUseCaseInSuccess() = runTest{
        `when`(getMovieRepository.getMovies()).thenThrow(
            RuntimeException("Something Went Wrong")
        )
        getMovieUseCase.invoke().onEach {
            Assert.assertEquals("Something Went Wrong",it.message)
        }
    }

}