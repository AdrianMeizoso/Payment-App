package com.adrian.payment.contacts.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.adrian.payment.R
import com.adrian.payment.common.NetworkState
import com.adrian.payment.common.observe
import com.adrian.payment.contacts.domain.paging.ContactsAdapter
import com.adrian.payment.contacts.domain.model.Contact
import com.adrian.payment.contacts.domain.viewmodel.MainViewModel
import com.adrian.payment.contacts.state.ContactListState
import kotlinx.android.synthetic.main.fragment_list.*
import org.koin.androidx.viewmodel.ext.sharedViewModel

class ListFragment : Fragment(), ContactsAdapter.OnContactListener {

    private lateinit var adapterContacts: ContactsAdapter
    private val mainViewModel: MainViewModel by sharedViewModel()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initGamesRecycler()

        observe(mainViewModel.state) { mainViewModelState ->
            when (mainViewModelState) {
                is ContactListState.MainLoading -> {
                    progress_circular_initial.visibility = View.VISIBLE
                }
                is ContactListState.MainLoadSuccess -> {
                    progress_circular_initial.visibility = View.GONE
                    pay_button.visibility = View.GONE
                }
                is ContactListState.MainLoadError -> {
                    progress_circular_initial.visibility = View.GONE
                }
                is ContactListState.SecondLoading -> {
                    progress_circular.visibility = View.VISIBLE
                }
                is ContactListState.SecondLoadSuccess -> {
                    progress_circular.visibility = View.GONE
                }
                is ContactListState.SecondLoadError -> {
                    progress_circular.visibility = View.GONE
                }
                is ContactListState.Selecting -> {
                    pay_button.visibility = View.VISIBLE
                }
                is ContactListState.RePainting -> {
                    adapterContacts.setItemSelected(mainViewModelState.newPosContact)
                    mainViewModel.onRepainting()
                }
            }
        }

        observe(mainViewModel.networkState) { state ->
            when (state){
                is NetworkState.Loading -> mainViewModel.onLoadingSecondNetworkState()
                is NetworkState.Success -> mainViewModel.onSuccessSecondNetworkState()
                is NetworkState.Error -> mainViewModel.onErrorSecondNetworkState(state.throwable)
            }
        }

        observe(mainViewModel.initialLoaderState) { state ->
            when (state){
                is NetworkState.Loading -> mainViewModel.onLoadingMainNetworkState()
                is NetworkState.Success -> mainViewModel.onSuccessMainNetworkState()
                is NetworkState.Error -> mainViewModel.onErrorMainNetworkState(state.throwable)
            }
        }
    }

    // ContactsAdapter.OnContactListener

    override fun onClickedContactListener(contact: Contact, position: Int) {
        mainViewModel.onClickedContactListener(contact, position)
    }

    override fun onLongClickedContactListener(contact: Contact, position: Int) {
        mainViewModel.onLongContactClicked(contact, position)
    }

    //Private methods

    private fun initGamesRecycler() {
        val linearLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        adapterContacts = ContactsAdapter(this)
        games_recycler.layoutManager = linearLayoutManager
        games_recycler.adapter = adapterContacts
        games_recycler.layoutAnimation = AnimationUtils.loadLayoutAnimation(context,
                R.anim.item_list_layout_anim)
        observe(mainViewModel.gamesList) {
            adapterContacts.submitList(it)
        }
        pay_button.setOnClickListener {
            it.findNavController().navigate(R.id.action_listFragment_to_amountFragment)
        }
    }
}