package com.example.movieapp.movie_list

import com.example.movieapp.MainCoroutineRule
import com.example.movieapp.core.common.Resource
import com.example.movieapp.domain.model.list.Movie
import com.example.movieapp.domain.use_cases.GetMovieUseCase
import com.example.movieapp.presentation.viewmodels.MovieViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

@OptIn(ExperimentalCoroutinesApi::class)
class MovieViewModelShould {

    @get:Rule
    val mainCoroutineRule =  MainCoroutineRule()

    private val getMovieUseCase : GetMovieUseCase = mock()
    private lateinit var movieViewModel : MovieViewModel
    private val movieList : List<Movie>? = mock()

    @Before
    fun setup(){
        movieViewModel =  MovieViewModel(getMovieUseCase)
    }

    @Test
    fun validateUserWillSeeProgressBarInitially() = runTest {
        `when`(getMovieUseCase.invoke()).thenReturn(
            flow {
                emit(Resource.Loading())
            }
        )
        mainCoroutineRule.testDispatcher.scheduler.advanceUntilIdle()
        Assert.assertEquals(true,movieViewModel.movieListStateHolder.value.isLoading)
    }

    @Test
    fun validateSuccessStateIsStoredInMovieList() = runTest {
        `when`(getMovieUseCase.invoke()).thenReturn(
            flow {
                emit(Resource.Success(movieList))
            }
        )
        mainCoroutineRule.testDispatcher.scheduler.advanceUntilIdle()
        Assert.assertEquals(movieList,movieViewModel.movieListStateHolder.value.data)
    }

    @Test
    fun throwErrorInErrorCase() = runTest {
        `when`(getMovieUseCase.invoke()).thenReturn(
            flow {
                emit(Resource.Error("Something Went Wrong"))
            }
        )
        mainCoroutineRule.testDispatcher.scheduler.advanceUntilIdle()
        Assert.assertEquals("Something Went Wrong",movieViewModel.movieListStateHolder.value.error)
    }

}