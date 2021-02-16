package com.techpaliyal.androidkotlinmvvm.extensions

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide


@BindingAdapter("tool:loadUrl")
fun loadUrl(imageView: ImageView?, loadUrl: String) {
    imageView?.let {

        Glide.with(imageView).load(loadUrl).into(imageView)
    }
}