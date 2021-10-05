package com.techpaliyal.androidkotlinmvvm.ui.multiple_view_example_2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.techpaliyal.androidkotlinmvvm.ui.multiple_view.ListItemModel
import com.yogeshpaliyal.universalAdapter.utils.Resource

class MultipleViewChatViewModel : ViewModel() {

    private val _sampleList = MutableLiveData<Resource<List<ChatListing>>>()
    val sampleList : LiveData<Resource<List<ChatListing>>> = _sampleList


    init {
        loadDummyData()
    }

    private fun loadDummyData(){
        val tempArray = ArrayList<ChatListing>()

        tempArray.add(HeadingModel("Yesterday"))
        tempArray.add(ListItems("Wassup","1:40 PM","MessageSent"))
        tempArray.add(ListItems("Just preparing for exams","1:43 PM","MessageReceived"))
        tempArray.add(ListItems("Great!","1:47 PM","MessageSent"))


        tempArray.add(HeadingModel("Today"))
        tempArray.add(ListItems("Hello","12:25 AM","MessageSent"))
        tempArray.add(ListItems("I am fine","12:27 AM","MessageReceived"))
        tempArray.add(ListItems("What about you","12:27 AM","MessageReceived"))
        tempArray.add(ListItems("I am great!","12:30 AM","MessageSent"))

        _sampleList.value = Resource.success(tempArray)
    }

}