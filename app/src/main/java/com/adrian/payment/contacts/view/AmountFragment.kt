package com.adrian.payment.contacts.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.adrian.payment.R
import com.adrian.payment.contacts.domain.viewmodel.AmountViewModel
import com.adrian.payment.databinding.FragmentAmountBinding
import org.koin.androidx.viewmodel.ext.viewModel

class AmountFragment : Fragment() {

    lateinit var binding: FragmentAmountBinding
    private val amountViewModel: AmountViewModel by viewModel()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_amount, container, false)

        binding.viewModel = amountViewModel
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        amountViewModel.currentAmount.observe(this, Observer {
            binding.amount = it
        })

        amountViewModel.currentDecimals.observe(this, Observer {
            binding.decimals = it
        })

        amountViewModel.showDecimals.observe(this, Observer {
            binding.hasDecimals = it
        })
    }
}
