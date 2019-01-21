package com.adrian.payment.main.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.adrian.payment.R
import com.adrian.payment.common.formattedTime
import com.adrian.payment.common.setImageUrl
import com.adrian.payment.main.MainViewModel
import com.adrian.payment.main.MainViewModelFactory
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_detail.*
import javax.inject.Inject

class DetailFragment : DaggerFragment() {

    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory

    lateinit var mainViewModel: MainViewModel

    private var videoLink: String = ""

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mainViewModel = activity?.run {
            ViewModelProviders.of(this, mainViewModelFactory)
                .get(MainViewModel::class.java)
        } ?: throw Exception("Invalid Activity")

        initAttributes()
        setVideoButtonListener()
        setObservers()

        val gameId = arguments?.getString("gameId")
        val gamePos = arguments?.getInt("position")

        gameId?.let { mainViewModel.getSpeedRunByGameId(it) }

        val game = gamePos?.let { mainViewModel.getGameByPos(it) }
        game_name.text = game?.names?.international ?: getString(R.string.no_text)
        game_cover.setImageUrl(game?.assets?.coverLarge?.uri)
    }

    private fun initAttributes() {
        mainViewModel.runData = MutableLiveData()
        mainViewModel.userData = MutableLiveData()
        videoLink = ""
        game_name.text = getString(R.string.loading)
        user_name.text = getString(R.string.loading)
        run_time.text = getString(R.string.loading)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    //Private methods

    private fun setObservers() {
        mainViewModel.runData.observe(this, Observer {
            run_time.text = it.times?.primary_t?.formattedTime ?: getString(R.string.no_text)

            it.videos?.links?.get(0)?.uri?.let {videoLinkData ->
                video_button.isEnabled = true
                videoLink = videoLinkData
            }

            it.players?.get(0)?.id?.let { userId ->
                mainViewModel.getUserById(userId)
            } ?: run { user_name.text = getString(R.string.no_text) }
        })

        mainViewModel.userData.observe(this, Observer {
            user_name.text = it.names.international ?: getString(R.string.no_text)
        })
    }

    private fun setVideoButtonListener() {
        video_button.setOnClickListener {
            val location = Uri.parse(videoLink)
            val videoIntent = Intent(Intent.ACTION_VIEW, location)

            val packageManager = activity?.packageManager
            val activities = packageManager?.queryIntentActivities(videoIntent, 0)
            val isIntentSafe = (activities?.size ?: 0) > 0

            if (isIntentSafe) startActivity(videoIntent)
        }
    }
}