package com.adrian.payment.main.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.adrian.payment.R
import com.adrian.payment.main.MainViewModel
import com.adrian.payment.main.MainViewModelFactory
import com.adrian.payment.main.domain.GamesAdapter
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_list.*
import javax.inject.Inject

class ListFragment : DaggerFragment() {

    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory

    private val mainViewModel: MainViewModel by lazy {
        activity?.run {
            ViewModelProviders.of(this, mainViewModelFactory)
                .get(MainViewModel::class.java)
        } ?: throw Exception("Invalid Activity")
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initGamesRecycler()
    }

    //Private methods

    private fun initGamesRecycler() {
        val linearLayoutManager = GridLayoutManager(context, 2)
        val gamesAdapter = GamesAdapter()
        games_recycler.layoutManager = linearLayoutManager
        games_recycler.adapter = gamesAdapter
        mainViewModel.gamesList.observe(this, Observer {
            gamesAdapter.submitList(it)
        })
    }
}