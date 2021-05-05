package com.techpaliyal.androidkotlinmvvm.ui.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.techpaliyal.androidkotlinmvvm.model.UserModel


/**
 * @author Yogesh Paliyal
 * yogeshpaliyal.foss@gmail.com
 * https://techpaliyal.com
 * https://yogeshpaliyal.com
 * Created Date : 9 January 2020
 */
class UserListingActivityViewModel : ViewModel() {

    val data = MutableLiveData<ArrayList<UserModel>>()



    fun setData(){
        val tempArr = ArrayList<UserModel>()
        tempArr.add(UserModel(name = "Yogesh",image = "https://randomuser.me/api/portraits/men/52.jpg", address = "Jodhpur"))
        tempArr.add(UserModel(name = "Umesh",image = "https://randomuser.me/api/portraits/men/62.jpg"))
        tempArr.add(UserModel(name = "Sohan",image = "https://randomuser.me/api/portraits/men/84.jpg"))
        tempArr.add(UserModel(name = "Madan",image = "https://randomuser.me/api/portraits/men/83.jpg"))
        data.value = tempArr
    }

}