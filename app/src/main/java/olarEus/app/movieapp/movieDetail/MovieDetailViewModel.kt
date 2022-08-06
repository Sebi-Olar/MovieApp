package olarEus.app.movieapp.movieDetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import olarEus.app.movieapp.network.APIClient
import olarEus.app.movieapp.ui.searchScreen.ui.home.MovieRemoteDataSource

class MovieDetailViewModel : ViewModel() {

    val currentMovieId = MutableLiveData<Int>()
    var movie: MovieDetails? = null
    private val movieRemoteDataSource = MovieRemoteDataSource(APIClient.instance.retrofit)

    fun getMovieDetails(): MovieDetails? {
        return currentMovieId.value?.let { movieRemoteDataSource.getMovieDetails(it) }
    }
}