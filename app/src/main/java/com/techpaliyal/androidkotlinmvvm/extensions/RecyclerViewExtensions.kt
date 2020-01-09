package com.techpaliyal.androidkotlinmvvm.extensions

import androidx.annotation.LayoutRes
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.techpaliyal.androidkotlinmvvm.R
import com.techpaliyal.androidkotlinmvvm.model.BasicModel
import com.techpaliyal.androidkotlinmvvm.ui.adapter.UniversalRecyclerAdapter


/**
 * @author Yogesh Paliyal
 * Created Date : 9 January 2020
 */

@BindingAdapter(value = ["tools:adapter","tools:data","tools:itemList", "tools:itemListener"], requireAll = false)
fun <T> setAdapter(recyclerView: RecyclerView,adapter: UniversalRecyclerAdapter<T>?, data : MutableLiveData<ArrayList<T>>, @LayoutRes listItem : Int = R.layout.item_simple, itemListener: Any){
    if (recyclerView.adapter == null){
        val tempAdapter = if (adapter == null){
          UniversalRecyclerAdapter(listItem, data.value ?: ArrayList(), itemListener)
        }else{
            adapter
        }
        recyclerView.adapter = tempAdapter
    }else{
        if (recyclerView.adapter is UniversalRecyclerAdapter<*>) {
            val items = data.value ?: ArrayList()
            (recyclerView.adapter as UniversalRecyclerAdapter<T>).updateData(items)
        }
    }
}