package olarEus.app.movieapp.ui.actorsScreen

import olarEus.app.movieapp.network.executeAndDeliver
import olarEus.app.movieapp.utils.Constants.API_KEY
import olarEus.app.movieapp.utils.Constants.LANGUAGE
import retrofit2.Retrofit

class ActorsRemoteDataSource(retrofit: Retrofit) {
    private val apiService: ActorsApiService = retrofit.create(ActorsApiService::class.java)
    private val actorMapper = ActorMapper()

    fun getActors(): List<Actor> {
        return apiService.getActors(API_KEY, LANGUAGE)
            .executeAndDeliver().actors.map { actorMapper.map(it) }
    }
}