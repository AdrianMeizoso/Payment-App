package com.adrian.payment.contacts.domain.viewmodel

import android.view.View
import androidx.core.os.bundleOf
import androidx.lifecycle.MutableLiveData
import androidx.navigation.findNavController
import com.adrian.payment.R
import com.adrian.payment.common.BaseViewModel
import com.adrian.payment.common.VoidState
import com.adrian.payment.contacts.state.ContactListState

class AmountViewModel : BaseViewModel<VoidState>() {

    val currentAmount: MutableLiveData<Int> = MutableLiveData()
    val currentDecimals: MutableLiveData<String> = MutableLiveData()
    val showDecimals: MutableLiveData<Boolean> = MutableLiveData()

    companion object {
        const val MAX_AMOUNT: Int = 1000
        const val MAX_DECIMALS: Int = 2
        const val MULTIPLIER: Int = 10
    }

    init {
        currentAmount.value = 0
        currentDecimals.value = ""
        showDecimals.value = false
    }

    fun numberClicked(amount: Int, decimals: String, number: Int) {
        showDecimals.value?.let { hasDecimals ->
            if (hasDecimals) setDecimals(decimals, number)
            else setAmount(amount, number)
        }
    }

    fun deleteElemClicked(amount: Int, decimalsParam: String) {
        showDecimals.value?.let { hasDecimals ->
            if (hasDecimals) {
                currentDecimals.value?.let { decimals ->
                    currentDecimals.value = decimalsParam.dropLast(1)
                    if (decimals.isBlank()) showDecimals.value = false
                }
            } else {
                currentAmount.value = amount / MULTIPLIER
            }
        }
    }

    fun dotClicked(amount: Int) {
        if (amount < MAX_AMOUNT) showDecimals.value = true
    }

    fun pay(view: View) {
        val bundleAmount = bundleOf("amount" to
                (currentAmount.value.toString() + "." + currentDecimals.value).toFloat())
        view.findNavController().navigate(R.id.action_amountFragment_to_resultFragment, bundleAmount)
    }

    // Private methods
    private fun setDecimals(decimals: String, number: Int) {
        when {
            decimals.isBlank() -> currentDecimals.value = number.toString()
            else -> {
                if ((decimals + number.toString()).count() <= MAX_DECIMALS)
                    currentDecimals.value = decimals + number.toString()
            }
        }
    }

    private fun setAmount(amount: Int, number: Int) {
        when {
            amount <= 0 -> currentAmount.value = number
            else -> currentAmount.value = amount * MULTIPLIER + number
        }
        if (amount * MULTIPLIER + number >= MAX_AMOUNT) currentAmount.value = MAX_AMOUNT
    }
}