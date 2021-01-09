package com.yogeshpaliyal.universal_adapter.utils

import androidx.annotation.LayoutRes
import androidx.lifecycle.LifecycleOwner


/*
* @author Yogesh Paliyal
* techpaliyal@gmail.com
* https://techpaliyal.com
* created on 08-01-2021 19:54
*/
class  UniversalAdapterOptions <T>( @LayoutRes
                               val resource: Int,
                               @LayoutRes
                               val resourceLoading: Int? = null,
                               val defaultLoadingItems: Int = 5,
                               @LayoutRes
                               val loaderFooter: Int? = null,
                               var data: Resource<ArrayList<T>?>?= null,
                               @LayoutRes
                               val errorLayout: Int? = null,
                               var errorListener: Any? = null,
                               var mListener: Any? = null,
                               @LayoutRes
                               val noDataLayout: Int? = null,
                               var noDataListener: Any? = null,
                               var lifecycleOwner: LifecycleOwner ?= null) {




}