package com.adrian.payment.contacts.domain

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adrian.payment.contacts.domain.model.Contact
import com.adrian.payment.databinding.ContactItemBinding

class ContactsViewHolder(private val binding: ContactItemBinding) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun create(parent: ViewGroup): ContactsViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val itemBinding = ContactItemBinding.inflate(layoutInflater, parent, false)
            return ContactsViewHolder(itemBinding)
        }
    }

    fun bind(contact: Contact, position: Int) {
        binding.contact = contact
        binding.elemPos = position
        binding.executePendingBindings()
    }
}