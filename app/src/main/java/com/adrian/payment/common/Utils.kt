package com.adrian.payment.common

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.adrian.payment.R
import com.adrian.payment.common.injection.GlideApp
import java.security.MessageDigest

@BindingAdapter("imageUrl")
fun ImageView.setImageUrl(url: String?) {
    GlideApp.with(context).load(url).placeholder(R.drawable.ic_account_circle_black_60dp).into(this)
}

@BindingAdapter("circleImageUrl")
fun ImageView.setCircleImageUrl(url: String?) {
    GlideApp.with(context).load(url).circleCrop().placeholder(R.drawable.ic_account_circle_black_60dp).into(this)
}

fun String.md5() : String {
    val md = MessageDigest.getInstance("MD5")
    val digested = md.digest(toByteArray())
    return digested.joinToString("") {
        String.format("%02x", it)
    }
}