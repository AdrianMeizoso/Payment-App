package com.adrian.payment.contacts.domain.paging

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adrian.payment.contacts.domain.model.Contact

class SelectedContactsAdapter(private val contactsList: List<Contact>,
                              private val amount: String) : RecyclerView.Adapter<ContactsSelectedViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsSelectedViewHolder =
            ContactsSelectedViewHolder.create(parent)

    override fun getItemCount(): Int = contactsList.size

    override fun onBindViewHolder(holder: ContactsSelectedViewHolder, position: Int) {
        val contact = contactsList[position]
        holder.bind(contact, position, amount)
    }
}