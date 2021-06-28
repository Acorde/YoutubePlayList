package com.igor.youtubeplaylists.ui.youtube

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.igor.youtubeplaylists.BuildConfig
import com.igor.youtubeplaylists.databinding.ActivityYoutubePlayerBinding

class YoutubePlayerActivity : YouTubeBaseActivity() {

    private var _binding: ActivityYoutubePlayerBinding? = null
    private val binding get() = _binding

    private var youtubeListener: YouTubePlayer.OnInitializedListener? = null

    companion object {
        private const val YOUTUBE_VIDEO_ID_EXTRA_DATA = "YOUTUBE_VIDEO_ID_EXTRA_DATA_"

        fun navigateToYoutubeActivity(activity: Activity, videoId: String) {
            Intent(activity, YoutubePlayerActivity::class.java).apply {
                this.putExtra(YOUTUBE_VIDEO_ID_EXTRA_DATA, videoId)
                activity.startActivity(this)
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityYoutubePlayerBinding.inflate(layoutInflater)
        val view = binding?.root
        setContentView(view)
        setYoutubePlayer()


    }

    private fun setYoutubePlayer() {
        getExtraData { videoId ->
            youtubeListener = object : YouTubePlayer.OnInitializedListener {
                override fun onInitializationSuccess(
                    p0: YouTubePlayer.Provider?,
                    youTubePlayer: YouTubePlayer?,
                    p2: Boolean
                ) {
                    youTubePlayer?.setFullscreen(true)
                    youTubePlayer?.loadVideo(videoId)
                }

                override fun onInitializationFailure(
                    p0: YouTubePlayer.Provider?,
                    p1: YouTubeInitializationResult?
                ) {
                    //TODO... Show Initialization error
                }

            }
        }


        binding?.youtubePlayer?.initialize(
            BuildConfig.YOUTUBE_API_KEY,
            youtubeListener
        )
    }

    private fun getExtraData(function: (String) -> Unit) {
        intent.getStringExtra(YOUTUBE_VIDEO_ID_EXTRA_DATA).let {
            it?.let {
                function.invoke(it)
            }
        }
    }
}