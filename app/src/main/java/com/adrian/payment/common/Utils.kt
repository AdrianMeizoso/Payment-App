package com.adrian.payment.common

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.adrian.payment.R
import com.adrian.payment.common.injection.GlideApp
import java.security.MessageDigest

//ImageView functions

@BindingAdapter("imageUrl")
fun ImageView.setImageUrl(url: String?) {
    GlideApp.with(context).load(url).placeholder(R.drawable.ic_account_circle_black_60dp).into(this)
}

@BindingAdapter("circleImageUrl")
fun ImageView.setCircleImageUrl(url: String?) {
    GlideApp.with(context).load(url).circleCrop().placeholder(R.drawable.ic_account_circle_black_60dp).into(this)
}

//String functions

fun String.md5() : String {
    val md = MessageDigest.getInstance("MD5")
    val digested = md.digest(toByteArray())
    return digested.joinToString("") {
        String.format("%02x", it)
    }
}

//LifeCycleOwner functions

fun <T : Any, L : LiveData<T>> LifecycleOwner.observe(liveData: L, body: (T) -> Unit) {
    liveData.observe(this, Observer(body))
}

//Arraylist functions

fun <T : Any, L : MutableLiveData<List<T>>> ArrayList<T>.addPostLiveData(param: T, liveData: L) {
    this.add(param)
    liveData.value = this
}

fun <T : Any, L : MutableLiveData<List<T>>> ArrayList<T>.removePostLiveData(param: T, liveData: L) {
    this.remove(param)
    liveData.value = this
}

fun <T : Any, L : MutableLiveData<List<T>>> ArrayList<T>.clearPostLiveData(liveData: L) {
    this.clear()
    liveData.value = this
}