package com.adrian.payment.contacts.domain

import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.adrian.payment.R
import com.adrian.payment.contacts.domain.model.Contact

class ContactsAdapter(private val onContactListener: OnContactListener) : PagedListAdapter<Contact, ContactsViewHolder>(contactDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsViewHolder {
        return ContactsViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ContactsViewHolder, position: Int) {
        val contact = getItem(position)
        contact?.let { contactItem ->
            holder.itemView.setOnLongClickListener {
                onContactListener.onLongClickedContactListener(contactItem, position)
                true
            }
            holder.itemView.setOnClickListener {
                onContactListener.onClickedContactListener(contact, position)
            }
            holder.bind(contact, position)
        }
    }

    fun setItemSelected(position: Int) {
        val contact = getItem(position)
        contact?.let { contactItem ->
            currentList?.get(position)?.selected = contactItem.selected.not()
            notifyItemChanged(position)
        }
    }

    fun setItemSelected(contact: Contact) {
        currentList?.indexOf(contact)?.let {
            currentList?.set(it, contact.copy(selected = contact.selected.not()))
            notifyItemChanged(it)
        }
    }

    companion object {
        val contactDiffCallback = object : DiffUtil.ItemCallback<Contact>() {
            override fun areItemsTheSame(oldItem: Contact, newItem: Contact): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: Contact, newItem: Contact): Boolean {
                return oldItem == newItem
            }
        }
    }

    interface OnContactListener {
        fun onClickedContactListener(contact: Contact, position: Int)
        fun onLongClickedContactListener(contact: Contact, position: Int)
    }
}