package com.adrian.payment.contacts.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.adrian.payment.R
import com.adrian.payment.common.observe
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

        observe(amountViewModel.currentAmount) { binding.amount = it }
        observe(amountViewModel.currentDecimals) { binding.decimals = it }
        observe(amountViewModel.showDecimals) { binding.hasDecimals = it }
    }
}
