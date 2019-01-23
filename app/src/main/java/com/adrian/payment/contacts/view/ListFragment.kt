package com.adrian.payment.contacts.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.adrian.payment.R
import com.adrian.payment.contacts.domain.GamesAdapter
import com.adrian.payment.contacts.domain.viewmodel.MainViewModel
import com.adrian.payment.contacts.domain.viewmodel.MainViewModelFactory
import kotlinx.android.synthetic.main.fragment_list.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance


class ListFragment : Fragment(), KodeinAware {

    override val kodein by closestKodein()

    private val mainViewModelFactory: MainViewModelFactory by instance()
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
        val linearLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        val gamesAdapter = GamesAdapter()
        games_recycler.layoutManager = linearLayoutManager
        games_recycler.adapter = gamesAdapter
        mainViewModel.gamesList.observe(this, Observer {
            gamesAdapter.submitList(it)
            if (games_recycler.visibility != View.VISIBLE) games_recycler.visibility = View.VISIBLE
        })
    }
}