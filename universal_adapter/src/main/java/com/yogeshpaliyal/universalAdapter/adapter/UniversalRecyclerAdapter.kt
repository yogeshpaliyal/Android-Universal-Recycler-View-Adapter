package com.yogeshpaliyal.universalAdapter.adapter

import androidx.annotation.LayoutRes
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.RecyclerView
import com.yogeshpaliyal.universalAdapter.utils.Resource
import com.yogeshpaliyal.universalAdapter.utils.Status


/**
 * @author Yogesh Paliyal
 * Created Date : 15 October 2020
 */
@Suppress
class UniversalRecyclerAdapter<T> constructor(val adapterBuilder: Builder<T>) {

    @Suppress
    @Deprecated(
        "Use UniversalAdapterBuilder for better approach",
        ReplaceWith(
            "UniversalAdapterBuilder(lifecycleOwner,data,UniversalAdapterViewType.Content(resource,listener = mListener)," +
                    "UniversalAdapterViewType.Loading(resourceLoading)," +
                    "UniversalAdapterViewType.LoadingFooter(loaderFooter)," +
                    "UniversalAdapterViewType.NoData(noDataLayout, listener = noDataListener)," +
                    "UniversalAdapterViewType.Error(errorLayout, listener = errorListener)).build()",
            "com.yogeshpaliyal.universal_adapter.utils.UniversalAdapterBuilder",
            "com.yogeshpaliyal.universal_adapter.adapter.UniversalAdapterViewType.Content",
            "com.yogeshpaliyal.universal_adapter.adapter.UniversalAdapterViewType.Loading",
            "com.yogeshpaliyal.universal_adapter.adapter.UniversalAdapterViewType.LoadingFooter",
            "com.yogeshpaliyal.universal_adapter.adapter.UniversalAdapterViewType.NoData",
            "com.yogeshpaliyal.universal_adapter.adapter.UniversalAdapterViewType.Error"
        )
    )
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
    ) :
            this(
                Builder<T>(
                    lifecycleOwner,
                    data,
                    UniversalAdapterViewType.Content(resource, mListener),
                    UniversalAdapterViewType.Loading(resourceLoading, defaultLoadingItems),
                    UniversalAdapterViewType.LoadingFooter(loaderFooter),
                    UniversalAdapterViewType.NoData(noDataLayout, noDataListener),
                    UniversalAdapterViewType.Error(errorLayout, errorListener)
                )
            )


    // content type
    // loading type
    // content + loading type
    // error layout
    // content + error layout

    private val mainAdapter: ConcatAdapter = ConcatAdapter()

    private var contentAdapter: ContentListAdapter<T>? = null
    private var loadingAdapter: LoadingAdapter<T>? = null
    private var noDataFound: NoDataAdapter<T>? = null // to support only message
    private var loadMoreAdapter: LoadingFooterAdapter<T>? = null
    private var errorAdapter: ErrorAdapter<T>? = null

    private var data: Resource<List<T>?>? = null

    init {
        if (adapterBuilder.content != null)
            contentAdapter = ContentListAdapter(
                adapterBuilder.lifecycleOwner,
                adapterBuilder.content
            )

        if (adapterBuilder.loading?.resourceLoading != null)
            loadingAdapter = LoadingAdapter(
                adapterBuilder.lifecycleOwner,
                adapterBuilder.loading
            )

        if (adapterBuilder.loadingFooter?.loaderFooter != null)
            loadMoreAdapter = LoadingFooterAdapter(
                adapterBuilder.lifecycleOwner,
                adapterBuilder.loadingFooter
            )

        if (adapterBuilder.error?.errorLayout != null)
            errorAdapter = ErrorAdapter(
                adapterBuilder.lifecycleOwner,
                adapterBuilder.error,
                adapterBuilder.data?.message ?: ""
            )

        if (adapterBuilder.noData?.noDataLayout != null)
            noDataFound = NoDataAdapter(
                adapterBuilder.lifecycleOwner,
                adapterBuilder.noData,
                adapterBuilder.data?.message ?: ""
            )

        adapterBuilder.data?.let { updateData(data = it) }
    }

    fun updateData(data: Resource<List<T>?>) {
        this.data = data
        setupAdapters(data)
    }

    fun getData() = data

    fun getContentAdapter() = contentAdapter
    fun getLoadMoreAdapter() = loadMoreAdapter
    fun getLoadingAdapter() = loadingAdapter
    fun getErrorAdapter() = errorAdapter
    fun getNoDataAdapter() = noDataFound

    fun getAdapter() = mainAdapter

    private fun setupAdapters(data: Resource<List<T>?>) {
        when (data.status) {
            Status.LOADING -> {
                // if has data then data + loading else loading
                remove(noDataFound)
                remove(errorAdapter)
                if (data.data.isNullOrEmpty()) {
                    // add only loading state
                    remove(contentAdapter)
                    remove(loadMoreAdapter)
                    addAdapter(loadingAdapter)

                } else {
                    remove(loadingAdapter)
                    addAdapter(contentAdapter)

                    contentAdapter?.submitList(data.data)
                    addAdapter(loadMoreAdapter)
                }

            }
            Status.SUCCESS -> {
                remove(loadingAdapter)
                remove(loadMoreAdapter)
                remove(errorAdapter)

                // check if there is data or note
                if (data.data.isNullOrEmpty()) {
                    // show no data found screen
                    remove(contentAdapter)

                    addAdapter(noDataFound)

                    noDataFound?.submitList(data.data)

                    noDataFound?.updateData(data.message)
                } else {
                    remove(noDataFound)

                    addAdapter(contentAdapter)
                    contentAdapter?.submitList(data.data)
                }
            }
            Status.ERROR -> {
                // if has data then data + error else error
                remove(loadMoreAdapter)
                remove(loadingAdapter)
                remove(noDataFound)
                if (data.data.isNullOrEmpty()) {

                    remove(contentAdapter)
                    // add only loading state

                    addAdapter(errorAdapter)
                    errorAdapter?.updateData(data.message)
                } else {

                    addAdapter(contentAdapter)
                    contentAdapter?.submitList(data.data)


                    addAdapter(errorAdapter)
                    errorAdapter?.updateData(data.message)
                }
            }
        }
    }

    private fun addAdapter(adapter: RecyclerView.Adapter<*>?) {
        if (isContains(adapter).not())
            adapter?.let { mainAdapter.addAdapter(adapter) }
    }

    private fun remove(adapter: RecyclerView.Adapter<*>?) {
        adapter?.let { mainAdapter.removeAdapter(adapter) }
    }


    private fun isContains(adapterToCheck: Any?): Boolean {
        for (adapter in mainAdapter.adapters) {
            if (adapter == adapterToCheck) {
                return true
            }
        }
        return false
    }

    @Suppress
    private fun clearMainAdapter() {
        for (adapter in mainAdapter.adapters) {
            mainAdapter.removeAdapter(adapter)
        }
    }

    data class Builder<T> constructor(
        var lifecycleOwner: LifecycleOwner? = null,
        var data: Resource<List<T>?>? = null,
        val content: UniversalAdapterViewType.Content<T>? = null,
        val loading: UniversalAdapterViewType.Loading<T>? = null,
        val loadingFooter: UniversalAdapterViewType.LoadingFooter<T>? = null,
        val noData: UniversalAdapterViewType.NoData<T>? = null,
        val error: UniversalAdapterViewType.Error<T>? = null
    ) {

        @Suppress
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

        fun build() = UniversalRecyclerAdapter(this)

        fun buildAdapter() = build().getAdapter()
    }

}
