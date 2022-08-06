package olarEus.app.movieapp.ui.genresScreen

import olarEus.app.movieapp.network.executeAndDeliver
import olarEus.app.movieapp.utils.Constants.API_KEY
import olarEus.app.movieapp.utils.Constants.LANGUAGE
import retrofit2.Retrofit

class GenreRemoteDataSource(retrofit: Retrofit) {
    private val apiService: GenresApiService = retrofit.create(GenresApiService::class.java)
    private val genreMapper = GenreMapper()
    fun getGenres(): List<Genre> {
        return apiService.getGenres(API_KEY, LANGUAGE)
            .executeAndDeliver().genres.map { genreMapper.map(it) }
    }
}