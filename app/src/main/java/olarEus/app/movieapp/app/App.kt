package olarEus.app.movieapp.app

import android.app.Application
import olarEus.app.movieapp.database.Database

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        Database.instance.initialise(this)
    }
}