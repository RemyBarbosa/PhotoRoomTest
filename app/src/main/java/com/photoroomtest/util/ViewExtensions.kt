package com.photoroomtest.util

import android.graphics.drawable.ColorDrawable
import android.util.Base64
import android.util.Base64.decode
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.photoroomtest.R

fun ImageView.loadImageB64(b64Image: String) {
    val imageByteArray: ByteArray = decode(b64Image, Base64.DEFAULT)
    Glide.with(this)
        .load(imageByteArray)
        .placeholder(ColorDrawable(ContextCompat.getColor(context, R.color.colorAccent)))
        .into(this)
}

fun ViewGroup.inflateFromParent(layoutId: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)
}

fun TextView.setTextOrInvisible(text: String?) {
    if (text.isNullOrBlank()) this.visibility = View.INVISIBLE else this.text = text
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.GONE
}