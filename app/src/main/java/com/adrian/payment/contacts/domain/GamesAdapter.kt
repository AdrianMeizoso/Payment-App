package com.adrian.payment.contacts.domain

import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.adrian.payment.R
import com.adrian.payment.contacts.domain.model.Contact

class GamesAdapter : PagedListAdapter<Contact, GamesViewHolder>(contactDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GamesViewHolder {
        return GamesViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: GamesViewHolder, position: Int) {
        val game = getItem(position)
        holder.itemView.setOnLongClickListener {
            currentList?.get(position)?.selected = currentList?.get(position)?.selected?.not()!!
            notifyItemChanged(position)
            true
        }
        holder.itemView.setOnClickListener {
            it.findNavController().navigate(R.id.action_listFragment_to_amountFragment)
        }
        game?.let { holder.bind(game, position) }
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
}