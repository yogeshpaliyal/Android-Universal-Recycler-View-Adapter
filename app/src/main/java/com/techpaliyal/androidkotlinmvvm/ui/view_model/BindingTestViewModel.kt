package com.techpaliyal.androidkotlinmvvm.ui.view_model

import android.app.Application
import androidx.lifecycle.*
import com.techpaliyal.androidkotlinmvvm.model.UserModel
import com.yogeshpaliyal.universal_adapter.utils.Resource
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class BindingTestViewModel() : ViewModel() {

    private val _usersData = MutableLiveData<Resource<List<UserModel>>>()
    val usersData : LiveData<Resource<List<UserModel>>> = _usersData


    init {
        loadData()
    }

    private fun loadData(){

        viewModelScope.launch {
            _usersData.postValue(Resource.loading())
            delay(3000)
            val tempArr = ArrayList<UserModel>()
            tempArr.add(UserModel(name = "Yogesh",image = "https://randomuser.me/api/portraits/men/52.jpg", address = "Jodhpur"))
            tempArr.add(UserModel(name = "Umesh",image = "https://randomuser.me/api/portraits/men/62.jpg"))
            tempArr.add(UserModel(name = "Sohan",image = "https://randomuser.me/api/portraits/men/84.jpg"))
            tempArr.add(UserModel(name = "Jitendra",image = "https://randomuser.me/api/portraits/men/83.jpg"))
            _usersData.postValue(Resource.success(tempArr))
        }

    }

}