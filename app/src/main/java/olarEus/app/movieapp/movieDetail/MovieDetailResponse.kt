package olarEus.app.movieapp.movieDetail

import com.google.gson.annotations.SerializedName

 data class MovieDetailResponse (
    var id: Int,

            @SerializedName("backdrop_patch") var backdropPath: String,
            @SerializedName("overview") var overview: String,
            @SerializedName("poster_path") var posterPath: String?,
            @SerializedName("release_date") var releaseDate: String,
            @SerializedName("title") var title: String,

            var videos:VideoList

 )