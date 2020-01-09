package com.techpaliyal.androidkotlinmvvm.ui.view_model

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.techpaliyal.androidkotlinmvvm.listeners.BasicListener
import com.techpaliyal.androidkotlinmvvm.model.BasicModel
import com.techpaliyal.androidkotlinmvvm.model.UserModel
import com.techpaliyal.androidkotlinmvvm.ui.adapter.UniversalRecyclerAdapter


/**
 * @author Yogesh Paliyal
 * Created Date : 9 January 2020
 */
class UserListingActivityViewModel(application: Application) : AndroidViewModel(application),
    BasicListener<UserModel> {
    override fun onClick(model: UserModel) {
        Toast.makeText(getApplication(), model.name, Toast.LENGTH_LONG).show()
    }

    val adapter : UniversalRecyclerAdapter<UserModel> ?= null
    val data = MutableLiveData<ArrayList<UserModel>>(ArrayList<UserModel>())
    val basicListener : BasicListener<UserModel> ?= this



    fun setData(){
        val tempArr = ArrayList<UserModel>()
        tempArr.add(UserModel(name = "Yogesh",image = "https://randomuser.me/api/portraits/men/52.jpg", address = "Jodhpur"))
        tempArr.add(UserModel(name = "Umesh",image = "https://randomuser.me/api/portraits/men/62.jpg"))
        tempArr.add(UserModel(name = "Sohan",image = "https://randomuser.me/api/portraits/men/84.jpg"))
        tempArr.add(UserModel(name = "Madan",image = "https://randomuser.me/api/portraits/men/83.jpg"))
        data.value = tempArr
    }

}