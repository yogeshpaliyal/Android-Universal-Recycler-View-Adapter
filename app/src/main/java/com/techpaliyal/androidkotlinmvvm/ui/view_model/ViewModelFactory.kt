package com.techpaliyal.androidkotlinmvvm.ui.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner


/**
 * @author Yogesh Paliyal
 * yogeshpaliyal.foss@gmail.com
 * https://techpaliyal.com
 * https://yogeshpaliyal.com
 * Created Date : 9 January 2020
 */
class ViewModelFactory :
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