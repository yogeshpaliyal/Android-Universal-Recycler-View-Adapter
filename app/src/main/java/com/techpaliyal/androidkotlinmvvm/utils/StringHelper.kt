package com.techpaliyal.androidkotlinmvvm.utils

import android.view.View
import androidx.databinding.BindingAdapter
import java.util.*


/**
 * @author Yogesh Paliyal
 * yogeshpaliyal.foss@gmail.com
 * https://techpaliyal.com
 * https://yogeshpaliyal.com
 * Created Date : 9 January 2020
 */
fun getRandomString()= UUID.randomUUID().toString()

@BindingAdapter("isSelected")
fun View.setIsSeleted(value : Boolean){
    isSelected = value
}