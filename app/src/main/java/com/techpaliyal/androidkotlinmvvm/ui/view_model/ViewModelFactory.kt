package com.techpaliyal.androidkotlinmvvm.ui.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner


/*
* @author Yogesh Paliyal
* techpaliyal@gmail.com
* https://techpaliyal.com
* created on 19-10-2020 21:38
*/
class ViewModelFactory() :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        /* if (modelClass.isAssignableFrom(SingleNetworkCallViewModel::class.java)) {
             return SingleNetworkCallViewModel(apiHelper, dbHelper) as T
         }*/

        return super.create(modelClass)
    }
}

fun <T : ViewModel> ViewModelStoreOwner.initViewModel(viewModel: Class<T>):T  = ViewModelProvider(this,
    ViewModelFactory()
).get(viewModel)