package olarEus.app.movieapp.ui.searchScreen.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import olarEus.app.movieapp.R
import olarEus.app.movieapp.utils.Constants.IMAGE_URL_MOVIE
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import olarEus.app.movieapp.movieDetail.MovieDetailViewModel

class MovieAdapter(private val movieList: List<Movie>,
                   private val detailsCallback: (() -> Unit)?,
                   private val viewModel: MovieDetailViewModel ):

RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val movieItem: LinearLayout = view.findViewById(R.id.itemMovie)
        val movieTitle: TextView = view.findViewById(R.id.tvMovieTitle)
        val movieDate: TextView = view.findViewById(R.id.tvMovieDate)
        val parentView: LinearLayout = view.findViewById(R.id.itemMovie)
        val movieDescription: TextView = view.findViewById(R.id.tvMovieDescription)
        val moviePhoto: ImageView = view.findViewById(R.id.iv_movie)
        val favouriteMovieIcon: ImageButton = view.findViewById(R.id.ibFavouriteMovie)
        val watchedMovieIcon: ImageButton = view.findViewById(R.id.ibWatchedMovie)
    }

    private var movieRepository = MovieRepository.instance

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movieList[position]
        holder.movieTitle.text = movie.title
        holder.movieDate.text=movie.release_date
        holder.movieDescription.text = movie.overview
        if(movie.backdrop_image!=null)
            Glide.with(holder.moviePhoto.context).load(IMAGE_URL_MOVIE + movie.poster_path).into(holder.moviePhoto)

        holder.parentView.setOnClickListener{
            viewModel.currentMovieId.postValue(movie.id)
            detailsCallback?.invoke()
        }

        holder.favouriteMovieIcon.setOnClickListener{
            movie.isFavourite = !movie.isFavourite
            selectFavouriteMovie(holder, movie)
            insertOrReplaceMovie(movie)
        }

        holder.watchedMovieIcon.setOnClickListener {
            movie.isWatched = !movie.isWatched
            selectWatchedMovie(holder, movie)
            insertOrReplaceMovie(movie)
        }

    }

    private fun insertOrReplaceMovie(movie: Movie){
        GlobalScope.launch(Dispatchers.IO) {
            movieRepository.insertOrReplace(movie)
        }
    }

    private fun selectFavouriteMovie(holder: ViewHolder, movie: Movie){
        holder.favouriteMovieIcon.setImageResource(
            when(movie.isFavourite){
                true -> R.drawable.ic_favourite_fill_red
                else -> R.drawable.ic_favourite_red
            }
        )
    }

    private fun selectWatchedMovie(holder: ViewHolder, movie: Movie){
        holder.watchedMovieIcon.setImageResource(
            when(movie.isWatched){
                true -> R.drawable.ic_watched_red
                else -> R.drawable.ic_watched_black
            }
        )
    }

    override fun getItemCount() = movieList.size


}