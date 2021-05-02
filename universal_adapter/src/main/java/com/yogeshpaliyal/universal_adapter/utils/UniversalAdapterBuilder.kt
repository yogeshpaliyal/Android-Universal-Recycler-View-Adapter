package com.yogeshpaliyal.universal_adapter.utils

import androidx.annotation.LayoutRes
import androidx.lifecycle.LifecycleOwner
import com.yogeshpaliyal.universal_adapter.adapter.UniversalAdapterViewType
import com.yogeshpaliyal.universal_adapter.adapter.UniversalBuilderNewExperiment


/*
* @author Yogesh Paliyal
* techpaliyal@gmail.com
* https://techpaliyal.com
* created on 08-01-2021 19:54
*/
open class UniversalAdapterBuilder<T> constructor(
    var lifecycleOwner: LifecycleOwner? = null,
    var data: Resource<List<T>?>? = null,
    val content: UniversalAdapterViewType.Content<T>? = null,
    val loading: UniversalAdapterViewType.Loading<T>? = null,
    val loadingFooter: UniversalAdapterViewType.LoadingFooter<T>? = null,
    val noData: UniversalAdapterViewType.NoData<T>? = null,
    val error: UniversalAdapterViewType.Error<T>? = null
) {
    constructor(
        @LayoutRes
        resource: Int,
        @LayoutRes
        resourceLoading: Int? = null,
        defaultLoadingItems: Int = 5,
        @LayoutRes
        loaderFooter: Int? = null,
        data: Resource<ArrayList<T>?>? = null,
        @LayoutRes
        errorLayout: Int? = null,
        errorListener: Any? = null,
        mListener: Any? = null,
        @LayoutRes
        noDataLayout: Int? = null,
        noDataListener: Any? = null,
        lifecycleOwner: LifecycleOwner? = null
    ) : this(
        lifecycleOwner,
        data,
        UniversalAdapterViewType.Content(resource, mListener),
        UniversalAdapterViewType.Loading(resourceLoading, defaultLoadingItems),
        UniversalAdapterViewType.LoadingFooter(loaderFooter),
        UniversalAdapterViewType.NoData(noDataLayout, noDataListener),
        UniversalAdapterViewType.Error(errorLayout, errorListener)
    )

    fun build() = UniversalBuilderNewExperiment(this)
}

