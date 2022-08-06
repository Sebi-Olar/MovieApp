package olarEus.app.movieapp.movieDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import olarEus.app.movieapp.R
import olarEus.app.movieapp.databinding.FragmentMovieDetailBinding

class MovieDetailFragment : Fragment(R.layout.fragment_movie_detail) {
    private var _binding: FragmentMovieDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: MovieDetailViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel =
            ViewModelProvider(requireActivity())[MovieDetailViewModel::class.java]

        _binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        GlobalScope.launch(Dispatchers.IO) {
            viewModel.movie = viewModel.getMovieDetails()
            withContext(Dispatchers.Main) {
                binding.Data.text = viewModel.movie?.releaseDate
                binding.Description.text = viewModel.movie?.overview
                binding.tvTitle.text = viewModel.movie?.title
                loadYtbVideos()
            }
        }

    }

    private fun loadYtbVideos() {
        binding.youtubePlayerView.addYouTubePlayerListener(object :
            AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                viewModel.movie?.videos?.results?.get(0)?.let {
                    youTubePlayer.loadVideo(findYoutubeTrailer(), 0f)
                }
            }
        })
    }

    private fun findYoutubeTrailer(): String {
        viewModel.movie?.videos?.results.let { videoList ->
            if (videoList != null) {
                for (video in videoList) {
                    if (video.type == "Trailer")
                        return video.key
                }
            }

        }
        return ""
    }
}