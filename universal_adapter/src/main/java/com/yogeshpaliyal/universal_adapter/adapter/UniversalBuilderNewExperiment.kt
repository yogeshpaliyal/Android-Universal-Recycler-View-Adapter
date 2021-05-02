package com.yogeshpaliyal.universal_adapter.adapter

import androidx.recyclerview.widget.ConcatAdapter
import com.yogeshpaliyal.universal_adapter.utils.Resource
import com.yogeshpaliyal.universal_adapter.utils.Status
import com.yogeshpaliyal.universal_adapter.utils.UniversalAdapterBuilder


/*
* @author Yogesh Paliyal
* techpaliyal@gmail.com
* https://techpaliyal.com
* created on 08-01-2021 19:47
*/

class UniversalBuilderNewExperiment<T>(val adapterBuilder: UniversalAdapterBuilder<T>) {

    // content type
    // loading type
    // content + loading type
    // error layout
    // content + error layout

    private val mainAdapter: ConcatAdapter = ConcatAdapter()

    private var contentAdapter: ContentListAdapter<T>? = null
    private var loadingAdapter: LoadingAdapter<T>? = null
    private var loadMoreAdapter: LoadingAdapter<T>? = null
    private var errorAdapter: ErrorAdapter<T>? = null

    init {
        if (adapterBuilder.content?.resource != null)
            contentAdapter = ContentListAdapter(
                adapterBuilder.lifecycleOwner,
                adapterBuilder.content.resource,
                adapterBuilder.content.listener,
                adapterBuilder.content.customBindingMapping
            )

        if (adapterBuilder.loading?.resourceLoading != null)
            loadingAdapter = LoadingAdapter(
                adapterBuilder.lifecycleOwner,
                adapterBuilder.loading.resourceLoading, adapterBuilder.loading.defaultLoadingItems
            )

        if (adapterBuilder.loadingFooter?.loaderFooter != null)
            loadMoreAdapter = LoadingAdapter(
                adapterBuilder.lifecycleOwner,
                adapterBuilder.loadingFooter.loaderFooter, 1
            )

        if (adapterBuilder.error?.errorLayout != null)
            errorAdapter = ErrorAdapter(
                adapterBuilder.lifecycleOwner,
                adapterBuilder.error.errorLayout,
                listener = adapterBuilder.error.listener,
                customBinding = adapterBuilder.error.customBindingMapping
            )
    }

    fun updateData(data: Resource<List<T>>) {
        setupAdapters(data)
    }

    fun build() = mainAdapter

    private fun setupAdapters(data: Resource<List<T>>) {
        when (data.status) {
            Status.LOADING -> {
                // if has data then data + loading else loading
                if (data.data.isNullOrEmpty()) {
                    // add only loading state
                    contentAdapter?.let { mainAdapter.removeAdapter(it) }
                    loadMoreAdapter?.let { mainAdapter.removeAdapter(it) }
                    errorAdapter?.let { mainAdapter.removeAdapter(it) }
                    if (isContains(loadingAdapter).not())
                        loadingAdapter?.let { mainAdapter.addAdapter(it) }
                } else {
                    loadingAdapter?.let { mainAdapter.removeAdapter(it) }
                    errorAdapter?.let { mainAdapter.removeAdapter(it) }
                    if (isContains(contentAdapter).not())
                        contentAdapter?.let { mainAdapter.addAdapter(it) }
                    contentAdapter?.submitList(data.data)
                    if (isContains(loadMoreAdapter).not())
                        loadMoreAdapter?.let { mainAdapter.addAdapter(it) }
                }

            }
            Status.SUCCESS -> {
                loadingAdapter?.let { mainAdapter.removeAdapter(it) }
                loadMoreAdapter?.let { mainAdapter.removeAdapter(it) }
                errorAdapter?.let { mainAdapter.removeAdapter(it) }

                if (isContains(contentAdapter).not())
                    contentAdapter?.let { mainAdapter.addAdapter(it) }
                contentAdapter?.submitList(data.data)
            }
            Status.ERROR -> {
                // if has data then data + error else error
                loadMoreAdapter?.let { mainAdapter.removeAdapter(it) }
                errorAdapter?.let { mainAdapter.removeAdapter(it) }
                if (data.data.isNullOrEmpty()) {

                    contentAdapter?.let { mainAdapter.removeAdapter(it) }
                    // add only loading state

                    if (isContains(errorAdapter).not())
                        errorAdapter?.let { mainAdapter.addAdapter(it) }
                } else {

                    if (isContains(contentAdapter).not())
                        contentAdapter?.let { mainAdapter.addAdapter(it) }
                    contentAdapter?.submitList(data.data)
                    if (isContains(errorAdapter).not())
                        errorAdapter?.let { mainAdapter.addAdapter(it) }
                }
            }
        }
    }


    fun isContains(adapterToCheck: Any?): Boolean {
        for (adapter in mainAdapter.adapters) {
            if (adapter == adapterToCheck) {
                return true
            }
        }
        return false
    }

    fun clearMainAdapter() {
        for (adapter in mainAdapter.adapters) {
            mainAdapter.removeAdapter(adapter)
        }
    }

}