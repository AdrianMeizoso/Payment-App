package com.adrian.payment.contacts.domain

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adrian.payment.contacts.domain.model.Contact
import com.adrian.payment.databinding.ContactSelectedItemBinding

class ContactsSelectedViewHolder(private val binding: ContactSelectedItemBinding) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun create(parent: ViewGroup): ContactsSelectedViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val itemBinding = ContactSelectedItemBinding.inflate(layoutInflater, parent, false)
            return ContactsSelectedViewHolder(itemBinding)
        }
    }

    fun bind(contact: Contact, position: Int, amount: String) {
        binding.amount = amount
        binding.contact = contact
        binding.elemPos = position
        binding.executePendingBindings()
    }
}