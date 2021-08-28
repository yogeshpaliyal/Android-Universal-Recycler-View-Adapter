package com.techpaliyal.androidkotlinmvvm.ui.multiple_view

import com.techpaliyal.androidkotlinmvvm.R
import com.yogeshpaliyal.universal_adapter.listener.UniversalViewType

data class ListItemModel(val name: String) : UniversalViewType,SchoolListing {
    override fun getLayoutId(): Int {
        return R.layout.item_list
    }
}