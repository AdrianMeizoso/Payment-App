package com.adrian.payment.main.domain

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.adrian.payment.main.domain.model.GameInfo

class GamesAdapter : PagedListAdapter<GameInfo, GamesViewHolder>(gameInfoDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GamesViewHolder {
        return GamesViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: GamesViewHolder, position: Int) {
        val game = getItem(position)
        game?.let { holder.bind(game, position) }
    }

    companion object {
        val gameInfoDiffCallback = object : DiffUtil.ItemCallback<GameInfo>() {
            override fun areItemsTheSame(oldItem: GameInfo, newItem: GameInfo): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: GameInfo, newItem: GameInfo): Boolean {
                return oldItem == newItem
            }
        }
    }
}