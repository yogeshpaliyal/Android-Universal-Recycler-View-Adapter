package com.techpaliyal.androidkotlinmvvm.ui.multiple_view_example_2

import com.techpaliyal.androidkotlinmvvm.R
import com.yogeshpaliyal.universalAdapter.listener.UniversalViewType

data class HeadingModel(val title: String) : UniversalViewType, ChatListing {


    override fun getLayoutId(): Int {
        return R.layout.item_chat_header
    }
}