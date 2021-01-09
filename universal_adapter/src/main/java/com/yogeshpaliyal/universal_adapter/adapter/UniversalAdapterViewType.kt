package com.yogeshpaliyal.universal_adapter.adapter

import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import com.yogeshpaliyal.universal_adapter.utils.Resource


/*
* @author Yogesh Paliyal
* techpaliyal@gmail.com
* https://techpaliyal.com
* created on 09-01-2021 12:03
*/
sealed class UniversalAdapterViewType {
    data class Content<T>(
        @LayoutRes
        val resource: Int,
        var mListener: Any? = null,
        val customBindingMapping : ((itemBinding : ViewDataBinding,item: T) -> Unit)?  = null
    )

    data class Loading<T>( @LayoutRes
                           val resourceLoading: Int? = null,
                           val defaultLoadingItems: Int = 5,
                           val customBindingMapping : ((itemBinding : ViewDataBinding,item: T) -> Unit)? = null)

    data class LoadingFooter<T>( @LayoutRes
                           val loaderFooter: Int? = null,
                                 val customBindingMapping : ((itemBinding : ViewDataBinding,item: T) -> Unit)? = null)

    data class NoData<T>( @LayoutRes
                           val noDataLayout: Int? = null,
                           val noDataListener: Any? = null,
                          val customBindingMapping : ((itemBinding : ViewDataBinding,item: T) -> Unit)?  = null)

    data class Error<T>( @LayoutRes
                          val errorLayout: Int? = null,
                          val errorListener: Any? = null,
                         val customBindingMapping : ((itemBinding : ViewDataBinding,item: Resource<ArrayList<T>?>?) -> Unit)?  = null)
}
