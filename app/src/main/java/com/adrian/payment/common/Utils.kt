package com.adrian.payment.common

import android.text.format.DateUtils
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.adrian.payment.R
import com.adrian.payment.common.injection.GlideApp
import java.security.MessageDigest

@BindingAdapter("imageUrl")
fun ImageView.setImageUrl(url: String?) {
    GlideApp.with(context).load(url).placeholder(R.drawable.ic_sand_watch).into(this)
}

@BindingAdapter("circleImageUrl")
fun ImageView.setCircleImageUrl(url: String?) {
    GlideApp.with(context).load(url).circleCrop().placeholder(R.drawable.ic_sand_watch).into(this)
}

fun String.md5() : String {
    val md = MessageDigest.getInstance("MD5")
    val digested = md.digest(toByteArray())
    return digested.joinToString("") {
        String.format("%02x", it)
    }
}

val String.formattedTime: String? get() {
    val timeSplit = this
        .substringAfter("PT")
        .substringBefore("S")
        .split("H", "M")

    return when (timeSplit.size) {
        1 -> "${timeSplit[0]}s"
        2 -> "${timeSplit[0]}m ${timeSplit[1]}s"
        3 -> "${timeSplit[0]}h ${timeSplit[1]}m ${timeSplit[2]}s"
        else -> null
    }
}

val Long.formattedTime: String? get() = DateUtils.formatElapsedTime(this)