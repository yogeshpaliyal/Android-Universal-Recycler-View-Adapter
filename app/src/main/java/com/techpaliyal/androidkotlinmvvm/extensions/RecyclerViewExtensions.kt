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
@BindingAdapter(value = ["tools:data","tools:itemList", "tools:itemListener"], requireAll = true)
fun <T> setAdapter(recyclerView: RecyclerView, data : MutableLiveData<ArrayList<T>>, @LayoutRes listItem : Int = R.layout.item_simple, itemListener: Any){
    if (recyclerView.adapter == null){
        recyclerView.adapter = UniversalRecyclerAdapter(listItem, data.value ?: ArrayList(), itemListener)
    }else{
        if (recyclerView.adapter is UniversalRecyclerAdapter<*>) {
            val items = data.value ?: ArrayList()
            (recyclerView.adapter as UniversalRecyclerAdapter<T>).updateData(items)
        }
    }
}