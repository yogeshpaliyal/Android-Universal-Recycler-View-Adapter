package com.techpaliyal.androidkotlinmvvm.ui.view_model

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.techpaliyal.androidkotlinmvvm.listeners.BasicListener
import com.techpaliyal.androidkotlinmvvm.model.BasicModel


/**
 * @author Yogesh Paliyal
 * Created Date : 9 January 2020
 */
class BasicListingActivityViewModel() : ViewModel(){

    val data = MutableLiveData<ArrayList<BasicModel>>(ArrayList<BasicModel>())



    fun fetchData(){
        val tempArr = ArrayList<BasicModel>()
        tempArr.add(BasicModel(name = "Yogesh"))
        tempArr.add(BasicModel(name = "Umesh"))
        tempArr.add(BasicModel(name = "Sohan"))
        tempArr.add(BasicModel(name = "Madan"))
        data.postValue(tempArr)
    }

}