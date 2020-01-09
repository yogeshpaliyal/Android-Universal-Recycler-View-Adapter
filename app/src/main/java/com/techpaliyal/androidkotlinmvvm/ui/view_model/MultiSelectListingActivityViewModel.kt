package com.techpaliyal.androidkotlinmvvm.ui.view_model

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.techpaliyal.androidkotlinmvvm.listeners.BasicListener
import com.techpaliyal.androidkotlinmvvm.model.BasicModel
import com.techpaliyal.androidkotlinmvvm.model.MultiSelectModel
import com.techpaliyal.androidkotlinmvvm.ui.adapter.UniversalRecyclerAdapter


/**
 * @author Yogesh Paliyal
 * Created Date : 9 January 2020
 */
class MultiSelectListingActivityViewModel(application: Application) : AndroidViewModel(application),
    BasicListener<MultiSelectModel> {
    override fun onClick(model: MultiSelectModel) {
      //  Toast.makeText(getApplication(), model.name, Toast.LENGTH_LONG).show()
        data.value?.forEach {
            Log.d("Testing","Name ${it.name} value => ${it.isChecked}")
        }
    }

    val adapter : UniversalRecyclerAdapter<MultiSelectModel> ?= null
    val data = MutableLiveData<ArrayList<MultiSelectModel>>(ArrayList<MultiSelectModel>())
    val basicListener : BasicListener<MultiSelectModel> ?= this



    fun setData(){
        if (data.value.isNullOrEmpty()) {
            val tempArr = ArrayList<MultiSelectModel>()
            tempArr.add(MultiSelectModel(name = "Apple", isChecked = true))
            tempArr.add(MultiSelectModel(name = "Banana", isChecked = false))
            tempArr.add(MultiSelectModel(name = "Cherry", isChecked = false))
            tempArr.add(MultiSelectModel(name = "Mango", isChecked = false))
            tempArr.add(MultiSelectModel(name = "Orange", isChecked = false))
            tempArr.add(MultiSelectModel(name = "Pineapple", isChecked = false))
            tempArr.add(MultiSelectModel(name = "Watermelon", isChecked = false))
            data.value = tempArr
        }
    }

    fun checkData(){
        data.value?.forEach {
            Log.d("Testing","Name ${it.name} value => ${it.isChecked}")
        }
    }

}