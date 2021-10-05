package com.techpaliyal.androidkotlinmvvm.ui.multiple_view_example_2

import com.techpaliyal.androidkotlinmvvm.R
import com.techpaliyal.androidkotlinmvvm.ui.multiple_view.SchoolListing
import com.yogeshpaliyal.universalAdapter.listener.UniversalViewType

data class ListItems(val name: String,val time:String,val type:String) : UniversalViewType, ChatListing {
    override fun getLayoutId(): Int {

        if(type == "MessageSent"){
            return R.layout.item_row_message_sent
        }
        else{
            return R.layout.item_row_message_received
        }



    }
}