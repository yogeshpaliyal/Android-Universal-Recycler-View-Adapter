package com.yogeshpaliyal.universal_adapter.adapter

import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding


/*
* @author Yogesh Paliyal
* yogeshpaliyal.foss@gmail.com
* https://techpaliyal.com
* created on 09-01-2021 12:03
*/
sealed class UniversalAdapterViewType {
    data class Content<T>(
        @LayoutRes
        val resource: Int,
        val listener: Any? = null,
        val additionalParams : HashMap<Int,Any>?= null,
        val customBindingMapping: ((itemBinding: ViewDataBinding, item: T, bindingAdapterPosition: Int) -> Unit)? = null
    )

    data class Loading<T>(
        @LayoutRes
        val resourceLoading: Int? = null,
        val defaultLoadingItems: Int = 5,
        val additionalParams : HashMap<Int,Any>?= null,
        val customBindingMapping: ((itemBinding: ViewDataBinding, item: T) -> Unit)? = null
    )

    data class LoadingFooter<T>(
        @LayoutRes
        val loaderFooter: Int? = null,
        val additionalParams : HashMap<Int,Any>?= null,
        val customBindingMapping: ((itemBinding: ViewDataBinding, item: T) -> Unit)? = null
    )

    data class NoData<T>(
        @LayoutRes
        val noDataLayout: Int? = null,
        val listener: Any? = null,
        val additionalParams : HashMap<Int,Any>?= null,
        val customBindingMapping: ((itemBinding: ViewDataBinding, item: String?) -> Unit)? = null
    )

    data class Error<T>(
        @LayoutRes
        val errorLayout: Int? = null,
        val listener: Any? = null,
        val additionalParams : HashMap<Int,Any>?= null,
        val customBindingMapping: ((itemBinding: ViewDataBinding, message: String?) -> Unit)? = null
    )
}
