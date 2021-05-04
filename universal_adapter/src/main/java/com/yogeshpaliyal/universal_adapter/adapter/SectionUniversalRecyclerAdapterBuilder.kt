package com.yogeshpaliyal.universal_adapter.adapter

import androidx.recyclerview.widget.ConcatAdapter
import com.yogeshpaliyal.universal_adapter.utils.Resource


/*
* @author Yogesh Paliyal
* techpaliyal@gmail.com
* https://techpaliyal.com
* created on 08-01-2021 19:47
*/
class SectionUniversalRecyclerAdapterBuilder<X : Any, Y : Any, Z : Any> constructor(
    contentOptions: UniversalRecyclerAdapter<X>? = null,
    headerOptions: UniversalRecyclerAdapter<Y>? = null,
    footerOptions: UniversalRecyclerAdapter<Z>? = null
) {


    constructor(
        contentBuilder: UniversalRecyclerAdapter.Builder<X>? = null,
        headerBuilder: UniversalRecyclerAdapter.Builder<Y>? = null,
        footerBuilder: UniversalRecyclerAdapter.Builder<Z>? = null
    ) : this(contentBuilder?.build(), headerBuilder?.build(), footerBuilder?.build())

    val concatedAdapter by lazy {
        val ada = ConcatAdapter()
        headerAdapter?.let {
            ada.addAdapter(it.getAdapter())
        }
        contentAdapter?.let {
            ada.addAdapter(it.getAdapter())
        }
        footerAdapter?.let {
            ada.addAdapter(it.getAdapter())
        }
        ada
    }

    var headerAdapter: UniversalRecyclerAdapter<Y>? = null
        private set

    var contentAdapter: UniversalRecyclerAdapter<X>? = null
        private set

    var footerAdapter: UniversalRecyclerAdapter<Z>? = null
        private set


    init {
        if (headerOptions != null) {
            headerAdapter = headerOptions
        }

        if (contentOptions != null) {
            contentAdapter = contentOptions
        }

        if (footerOptions != null) {
            footerAdapter = footerOptions
        }
    }

    fun build() = concatedAdapter


    fun updateContent(data: Resource<List<X>>) {
        contentAdapter?.updateData(data)
    }

    fun updateHeader(data: Resource<List<Y>>) {
        headerAdapter?.updateData(data)
    }

    fun updateFooter(data: Resource<List<Z>>) {
        footerAdapter?.updateData(data)
    }

}