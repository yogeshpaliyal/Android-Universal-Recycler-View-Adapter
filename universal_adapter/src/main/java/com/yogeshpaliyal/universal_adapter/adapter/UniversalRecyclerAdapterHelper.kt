package com.yogeshpaliyal.universal_adapter.adapter

import androidx.recyclerview.widget.ConcatAdapter
import com.yogeshpaliyal.universal_adapter.utils.Resource
import com.yogeshpaliyal.universal_adapter.utils.UniversalAdapterBuilder


/*
* @author Yogesh Paliyal
* techpaliyal@gmail.com
* https://techpaliyal.com
* created on 08-01-2021 19:47
*/

@Deprecated(
    "Helper is depricated use builder instead",
    ReplaceWith(
        "SectionUniversalRecyclerAdapterBuilder",
        "com.yogeshpaliyal.universal_adapter.adapter.SectionUniversalRecyclerAdapterBuilder"
    )
)
class UniversalRecyclerAdapterHelper<X : Any, Y : Any, Z : Any>(
    contentOptions: UniversalAdapterBuilder<X>? = null,
    headerOptions: UniversalAdapterBuilder<Y>? = null,
    footerOptions: UniversalAdapterBuilder<Z>? = null
){
    val concatedAdapter by lazy {
        val ada= ConcatAdapter()
        headerAdapter?.let{
            ada.addAdapter(it.getAdapter())
        }
        contentAdapter?.let{
            ada.addAdapter(it.getAdapter())
        }
        footerAdapter?.let{
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
            headerAdapter = UniversalRecyclerAdapter(headerOptions)
        }

        if (contentOptions != null) {
            contentAdapter = UniversalRecyclerAdapter(contentOptions)
        }

        if (footerOptions != null) {
            footerAdapter = UniversalRecyclerAdapter(footerOptions)
        }
    }


    fun updateContent(data: Resource<ArrayList<X>?>){
        contentAdapter?.updateData(data)
    }

    fun updateHeader(data: Resource<ArrayList<Y>?>){
        headerAdapter?.updateData(data)
    }

    fun updateFooter(data: Resource<ArrayList<Z>?>){
        footerAdapter?.updateData(data)
    }

}