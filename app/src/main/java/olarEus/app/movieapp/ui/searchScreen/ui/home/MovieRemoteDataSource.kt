package olarEus.app.movieapp.ui.searchScreen.ui.home

import olarEus.app.movieapp.movieDetail.MovieDetailMapper
import olarEus.app.movieapp.movieDetail.MovieDetails
import olarEus.app.movieapp.network.executeAndDeliver
import olarEus.app.movieapp.utils.Constants
import olarEus.app.movieapp.utils.Constants.API_KEY
import olarEus.app.movieapp.utils.Constants.LANGUAGE
import retrofit2.Retrofit

class MovieRemoteDataSource(retrofit: Retrofit) {
    private val apiService: MovieApiService = retrofit.create(MovieApiService::class.java)
    private val movieMapper = MovieMapper()

    private val movieDetailMapper = MovieDetailMapper()

    fun getMovies(withCast: String, withGenres: String): List<Movie> {
        return apiService.getMovies(API_KEY, LANGUAGE, withCast, withGenres)
            .executeAndDeliver().movies.map { movieMapper.map(it) }
    }

    fun getSearchedMovie(query: String): List<Movie> {
        return apiService.getSearchedMovie(API_KEY, LANGUAGE, query)
            .executeAndDeliver()
            .movies
            .map { movieMapper.map(it) }

    }

    fun getMovieDetails(movieId: Int): MovieDetails {
        return apiService.getMovieDetails(movieId,Constants.API_KEY, Constants.LANGUAGE,Constants.APPEND_TO_RESPONSE)
            .executeAndDeliver()
            .let { movieDetailMapper.map(it) }
    }
}