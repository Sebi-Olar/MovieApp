package olarEus.app.movieapp.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import olarEus.app.movieapp.ui.actorsScreen.Actor
import olarEus.app.movieapp.ui.actorsScreen.ActorDAO
import olarEus.app.movieapp.ui.genresScreen.Genre
import olarEus.app.movieapp.ui.genresScreen.GenreDAO
import olarEus.app.movieapp.ui.searchScreen.ui.home.Movie
import olarEus.app.movieapp.ui.searchScreen.ui.home.MovieDAO

class Database private constructor() {

    companion object{
        val instance= Database()
    }

    @androidx.room.Database(
        entities = [Genre::class, Actor::class, Movie:: class],
        version = 5
    )

    abstract class MovieAppDatabase: RoomDatabase(){
        abstract fun genresDao(): GenreDAO
        abstract fun actorsDao():ActorDAO
        abstract fun moviesDao(): MovieDAO
    }

    lateinit var movieAppDatabase: MovieAppDatabase
    private set

    fun initialise(context: Context ){
        this.movieAppDatabase= Room.databaseBuilder(
            context,
            MovieAppDatabase::class.java,
            "movie_app.db"
        ). fallbackToDestructiveMigration().build()
    }

}