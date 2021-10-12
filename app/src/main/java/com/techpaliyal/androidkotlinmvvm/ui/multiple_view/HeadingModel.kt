package com.techpaliyal.androidkotlinmvvm.ui.multiple_view

import com.techpaliyal.androidkotlinmvvm.R
import com.yogeshpaliyal.universalAdapter.listener.UniversalViewType

data class HeadingModel(val title: String) : UniversalViewType,SchoolListing {


    override fun getLayoutId(): Int {
        return R.layout.item_heading
    }
}