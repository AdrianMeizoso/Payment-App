package com.adrian.payment.main.domain

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.adrian.payment.R
import com.adrian.payment.databinding.GameItemBinding
import com.adrian.payment.main.domain.model.GameInfo

class GamesViewHolder(private val binding: GameItemBinding) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun create(parent: ViewGroup): GamesViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val itemBinding = GameItemBinding.inflate(layoutInflater, parent, false)
            return GamesViewHolder(itemBinding)
        }
    }

    fun bind(game: GameInfo, position: Int) {
        binding.game = game
        binding.elemPos = position
        binding.viewHolder = this
        binding.executePendingBindings()
    }

    fun onClickGame(view:View, position: Int, game: GameInfo) {
        val bundleGame = bundleOf("gameId" to game.id, "position" to position)
        view.findNavController().navigate(R.id.action_listFragment_to_detailFragment, bundleGame)
    }
}