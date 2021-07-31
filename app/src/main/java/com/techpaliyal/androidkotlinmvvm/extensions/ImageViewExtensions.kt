package com.techpaliyal.androidkotlinmvvm.extensions

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide


/**
 * @author Yogesh Paliyal
 * yogeshpaliyal.foss@gmail.com
 * https://techpaliyal.com
 * https://yogeshpaliyal.com
 * Created Date : 9 January 2020
 */
@BindingAdapter("tool:loadUrl")
fun loadUrl(imageView: ImageView?, loadUrl: String?) {
    imageView?.let {

        Glide.with(imageView).load(loadUrl).into(imageView)
    }
}