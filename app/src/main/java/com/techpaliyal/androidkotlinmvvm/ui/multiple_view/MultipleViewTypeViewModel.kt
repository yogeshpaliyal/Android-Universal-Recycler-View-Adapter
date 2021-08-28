package com.techpaliyal.androidkotlinmvvm.ui.multiple_view

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yogeshpaliyal.universal_adapter.utils.Resource

class MultipleViewTypeViewModel : ViewModel() {

    private val _sampleList = MutableLiveData<Resource<List<SchoolListing>>>()
    val sampleList : LiveData<Resource<List<SchoolListing>>> = _sampleList


    init {
        loadDummyData()
    }

    private fun loadDummyData(){
        val tempArray = ArrayList<SchoolListing>()
        tempArray.add(HeadingModel("Principal"))
        tempArray.add(ListItemModel("Yogesh Paliyal"))

        tempArray.add(HeadingModel("Staff"))
        tempArray.add(ListItemModel("Sachin Rupani"))
        tempArray.add(ListItemModel("Suraj Vaishnav"))
        tempArray.add(ListItemModel("Himanshu Choudhan"))
        tempArray.add(ListItemModel("Pramod Patel"))
        tempArray.add(ListItemModel("Bharath"))
        tempArray.add(ListItemModel("Sanjay"))
        tempArray.add(ListItemModel("Surendra Singh"))


        tempArray.add(HeadingModel("Students"))
        tempArray.add(ListItemModel("Bhoma Ram"))
        tempArray.add(ListItemModel("Deepak"))
        tempArray.add(ListItemModel("Sohan"))
        tempArray.add(ListItemModel("Umesh"))
        tempArray.add(ListItemModel("Amanda Howard"))
        tempArray.add(ListItemModel("Jeremy Glover"))
        tempArray.add(ListItemModel("Ginger Larson"))
        tempArray.add(ListItemModel("Lincoln Pierpoint"))
        tempArray.add(ListItemModel("Brian Brooks"))
        tempArray.add(ListItemModel("Erasmus Hall"))
        tempArray.add(ListItemModel("Amber Lane"))
        tempArray.add(ListItemModel("Elsie Cole"))

        _sampleList.value = Resource.success(tempArray)
    }

}