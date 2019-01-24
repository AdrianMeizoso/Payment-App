package com.adrian.payment.contacts.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.adrian.payment.R
import com.adrian.payment.contacts.domain.viewmodel.AmountModelFactory
import com.adrian.payment.contacts.domain.viewmodel.AmountViewModel
import com.adrian.payment.databinding.FragmentAmountBinding
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class AmountFragment : Fragment(), KodeinAware {

    override val kodein: Kodein by closestKodein()

    lateinit var binding: FragmentAmountBinding
    private val amountViewModelFactory: AmountModelFactory by instance()
    private val amountViewModel: AmountViewModel by lazy {
        activity?.run {
            ViewModelProviders.of(this, amountViewModelFactory)
                    .get(AmountViewModel::class.java)
        } ?: throw Exception("Invalid Activity")
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_amount, container, false)

        binding.viewModel = amountViewModel
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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

    override fun onDestroy() {
        super.onDestroy()
        amountViewModel.currentAmount.value = 0
    }
}
