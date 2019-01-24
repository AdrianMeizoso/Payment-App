package com.adrian.payment.contacts.domain.viewmodel

import android.view.View
import android.widget.Button
import androidx.lifecycle.MutableLiveData
import androidx.navigation.findNavController
import com.adrian.payment.R
import com.adrian.payment.common.BaseViewModel

class AmountViewModel : BaseViewModel() {

    val currentAmount: MutableLiveData<Int> = MutableLiveData()
    val currentDecimals: MutableLiveData<Int> = MutableLiveData()
    val showDecimals: MutableLiveData<Boolean> = MutableLiveData()

    companion object {
        const val MAX_AMOUNT: Int = 1000
        const val MAX_DECIMALS: Int = 99
        const val MULTIPLIER: Int = 10
    }

    init {
        currentAmount.value = 0
        currentDecimals.value = 0
        showDecimals.value = false
    }

    fun numberClicked(amount:Int, decimals:Int, number:Int) {
        showDecimals.value?.let { hasDecimals ->
            if (hasDecimals) {
                when {
                    decimals <= 0 -> currentDecimals.value = number
                    else -> currentDecimals.value = decimals*MULTIPLIER + number
                }
                if (decimals*MULTIPLIER + number >= MAX_DECIMALS) currentDecimals.value = MAX_DECIMALS
            } else {
                when {
                    amount <= 0 -> currentAmount.value = number
                    else -> currentAmount.value = amount*MULTIPLIER + number
                }
                if (amount*MULTIPLIER + number >= MAX_AMOUNT) currentAmount.value = MAX_AMOUNT
            }
        }
    }

    fun deleteElemClicked(amount: Int, decimalsParam: Int) {
        showDecimals.value?.let {hasDecimals ->
            if (hasDecimals) {
                currentDecimals.value?.let { decimals ->
                    when {
                        decimals <= 0 -> showDecimals.value = false
                        else -> currentDecimals.value = decimalsParam/MULTIPLIER
                    }
                }
            } else {
                currentAmount.value = amount/MULTIPLIER
            }
        }
    }

    fun dotClicked(amount: Int) {
        if (amount < MAX_AMOUNT) showDecimals.value = true
    }

    fun pay(view: View) {
        view.findNavController().navigate(R.id.action_amountFragment_to_resultFragment)
    }
}