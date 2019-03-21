package com.adrian.payment.contacts.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.adrian.payment.R
import com.adrian.payment.contacts.domain.paging.SelectedContactsAdapter
import com.adrian.payment.contacts.domain.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_result.*
import org.koin.androidx.viewmodel.ext.sharedViewModel

class ResultFragment : Fragment() {

    private val mainViewModel: MainViewModel by sharedViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_result, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val amount = arguments?.getFloat("amount")
        amount?.let {
            amount_text.text = String.format("%.2f€", it)
            val amountFormatted =
                    String.format("%.2f€", amount/mainViewModel.contactsSelected.size.toFloat())

            val linearLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            val contactsAdapter = SelectedContactsAdapter(mainViewModel.contactsSelected, amountFormatted)
            contacts_selected_recycler.layoutManager = linearLayoutManager
            contacts_selected_recycler.adapter = contactsAdapter

            return_button.setOnClickListener { view ->
                view.findNavController().navigate(R.id.action_resultFragment_to_listFragment)
            }
        }
    }
}