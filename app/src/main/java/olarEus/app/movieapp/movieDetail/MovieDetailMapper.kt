package olarEus.app.movieapp.movieDetail

class MovieDetailMapper {
    fun map(movieResponse: MovieDetailResponse): MovieDetails {
        return MovieDetails(
            id = movieResponse.id,
            title = movieResponse.title,
            backdropPath = movieResponse.backdropPath,
            overview = movieResponse.overview,
            posterPath = movieResponse.posterPath,
            releaseDate = movieResponse.releaseDate,
            videos = movieResponse.videos
        )

    }
}