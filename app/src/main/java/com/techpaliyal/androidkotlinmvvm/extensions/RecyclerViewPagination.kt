package com.techpaliyal.androidkotlinmvvm.extensions

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


/**
 * @author Yogesh Paliyal
 * yogeshpaliyal.foss@gmail.com
 * https://techpaliyal.com
 * https://yogeshpaliyal.com
 * Created Date : 9 January 2020
 */

const val RECYCLER_PAGING_THRESHOLD = 5

fun RecyclerView?.setupPagination(onReachThreshold : ()->Unit){
    this?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val linearLayoutManager =
                recyclerView.layoutManager as LinearLayoutManager?
            val totalItemCount = linearLayoutManager?.itemCount ?: 0
            val visibleItemCount = linearLayoutManager?.childCount ?: 0
            val lastVisibleItem = linearLayoutManager?.findLastVisibleItemPosition() ?: 0
            listScrolled(visibleItemCount, lastVisibleItem, totalItemCount,onReachThreshold)
        }
    })
}

private fun RecyclerView.listScrolled(
    visibleItemCount: Int,
    lastVisibleItemPosition: Int,
    totalItemCount: Int
    , onReachThreshold : ()->Unit) {
    val VISIBLE_THRESHOLD: Int = RECYCLER_PAGING_THRESHOLD
    if (visibleItemCount + lastVisibleItemPosition + VISIBLE_THRESHOLD >= totalItemCount) {
        onReachThreshold()
    }
}