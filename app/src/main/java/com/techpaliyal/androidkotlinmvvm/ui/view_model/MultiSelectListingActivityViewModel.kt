package com.techpaliyal.androidkotlinmvvm.ui.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.techpaliyal.androidkotlinmvvm.model.MultiSelectModel


/**
 * @author Yogesh Paliyal
 * yogeshpaliyal.foss@gmail.com
 * https://techpaliyal.com
 * https://yogeshpaliyal.com
 * Created Date : 9 January 2020
 */
class MultiSelectListingActivityViewModel : ViewModel(){

    val data = MutableLiveData<ArrayList<MultiSelectModel>>(ArrayList<MultiSelectModel>())



    fun fetchData(){
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

    fun logData(){
      /*  data.value?.forEach {
            Log.d("Testing","Name ${it.name} value => ${it.isChecked}")
        }*/
    }

}