package com.techpaliyal.androidkotlinmvvm.ui.view_model

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.techpaliyal.androidkotlinmvvm.listeners.BasicListener
import com.techpaliyal.androidkotlinmvvm.model.BasicModel
import com.techpaliyal.androidkotlinmvvm.ui.adapter.UniversalRecyclerAdapter


/**
 * @author Yogesh Paliyal
 * Created Date : 9 January 2020
 */
class BasicListingActivityViewModel(application: Application) : AndroidViewModel(application),
    BasicListener<BasicModel> {
    override fun onClick(model: BasicModel) {
        Toast.makeText(getApplication(), model.name, Toast.LENGTH_LONG).show()

    }

    val data = MutableLiveData<ArrayList<BasicModel>>(ArrayList<BasicModel>())
    val basicListener : BasicListener<BasicModel> ?= this



    fun setData(){
        val tempArr = ArrayList<BasicModel>()
        tempArr.add(BasicModel(name = "Yogesh"))
        tempArr.add(BasicModel(name = "Umesh"))
        tempArr.add(BasicModel(name = "Sohan"))
        tempArr.add(BasicModel(name = "Madan"))
        data.value = tempArr
    }

}