package com.adrian.payment.contacts.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.adrian.payment.R
import com.adrian.payment.common.observe
import com.adrian.payment.contacts.domain.ContactsAdapter
import com.adrian.payment.contacts.domain.model.Contact
import com.adrian.payment.contacts.domain.viewmodel.MainViewModel
import com.adrian.payment.contacts.domain.viewmodel.MainViewModelFactory
import kotlinx.android.synthetic.main.fragment_list.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class ListFragment : Fragment(), KodeinAware, ContactsAdapter.OnContactListener {

    override val kodein by closestKodein()

    private lateinit var adapterContacts: ContactsAdapter

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
        val init = arguments?.getBoolean("init")
        init?.let {
            if (init) initFragment()
            arguments?.clear()
        }
        initGamesRecycler()
    }

    // ContactsAdapter.OnContactListener

    override fun onClickedContactListener(contact: Contact, position: Int) {
        if (mainViewModel.contactsSelected.isNotEmpty()) {
            if (contact.selected) mainViewModel.removeContactData(contact)
            else mainViewModel.addContactSelected(contact)
            adapterContacts.setItemSelected(position)
        }
    }

    override fun onLongClickedContactListener(contact: Contact, position: Int) {
        if (contact.selected) mainViewModel.removeContactData(contact)
        else mainViewModel.addContactSelected(contact)
        adapterContacts.setItemSelected(position)
    }

    //Private methods

    private fun initFragment() {
        mainViewModel.clearContactSelected()
        mainViewModel.contactsSelectedData = MutableLiveData()
        mainViewModel.resetList()
    }

    private fun initGamesRecycler() {
        val linearLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        adapterContacts = ContactsAdapter(this)
        games_recycler.layoutManager = linearLayoutManager
        games_recycler.adapter = adapterContacts
        observe(mainViewModel.gamesList) {
            adapterContacts.submitList(it)
            if (games_recycler.visibility != View.VISIBLE) games_recycler.visibility = View.VISIBLE
            progress_circular.visibility = View.GONE
        }
        observe(mainViewModel.contactsSelectedData) {
            if (it.isNotEmpty()) pay_button.visibility = View.VISIBLE
            else pay_button.visibility = View.GONE
        }
        pay_button.setOnClickListener {
            it.findNavController().navigate(R.id.action_listFragment_to_amountFragment)
        }
    }
}