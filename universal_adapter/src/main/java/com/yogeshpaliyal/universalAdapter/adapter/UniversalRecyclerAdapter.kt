package com.yogeshpaliyal.universalAdapter.adapter

import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
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
        adapterBuilder.content?.let { content ->
            contentAdapter = ContentListAdapter(
                adapterBuilder.lifecycleOwner,
                content
            )
        }


        adapterBuilder.loading?.let { loading ->
            if (loading.resourceLoading != null)
                loadingAdapter = LoadingAdapter(
                    adapterBuilder.lifecycleOwner,
                    loading
                )
        }


        adapterBuilder.loadingFooter?.let { loadingFooter ->
            if (loadingFooter.loaderFooter != null)
                loadMoreAdapter = LoadingFooterAdapter(
                    adapterBuilder.lifecycleOwner,
                    loadingFooter
                )
        }


        adapterBuilder.error?.let { error ->
            errorAdapter = ErrorAdapter(
                adapterBuilder.lifecycleOwner,
                error,
                adapterBuilder.data?.message ?: ""
            )
        }

        adapterBuilder.noData?.let { noData ->
            noDataFound = NoDataAdapter(
                adapterBuilder.lifecycleOwner,
                noData,
                adapterBuilder.data?.message ?: ""
            )
        }


        adapterBuilder.data?.let { updateData(data = it) }
    }

    fun updateData(data: Resource<List<T>?>) {
        this.data = data
        setupAdapters(data)
    }

    /**
     * Get Resource data
     */
    fun getData() = data

    /**
     * get content adapter adapter which has content list adapter
     */
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
        internal var lifecycleOwner: LifecycleOwner? = null,
        internal var data: Resource<List<T>?>? = null,
        internal var content: UniversalAdapterViewType.Content<T>? = null,
        internal var loading: UniversalAdapterViewType.Loading<T>? = null,
        internal var loadingFooter: UniversalAdapterViewType.LoadingFooter<T>? = null,
        internal var noData: UniversalAdapterViewType.NoData<T>? = null,
        internal var error: UniversalAdapterViewType.Error<T>? = null
    ) {

        fun setLifecycleOwner(lifecycleOwner: LifecycleOwner?) {
            this.lifecycleOwner = lifecycleOwner
        }

        fun setData(data: Resource<List<T>?>?) {
            this.data = data
        }

        fun setContent(content: UniversalAdapterViewType.Content<T>) {
            this.content = content
        }

        @JvmOverloads
        fun setContent(
            @LayoutRes
            resource: Int? = null,
            listener: Any? = null,
            additionalParams: HashMap<Int, Any>? = null,
            customBindingMapping: ((itemBinding: ViewDataBinding, item: T, bindingAdapterPosition: Int) -> Unit)? = null
        ) {
            this.content = UniversalAdapterViewType.Content<T>(
                resource,
                listener,
                additionalParams,
                customBindingMapping
            )
        }

        fun setLoading(loading: UniversalAdapterViewType.Loading<T>) {
            this.loading = loading
        }

        @JvmOverloads
        fun setLoading(
            @LayoutRes
            resourceLoading: Int? = null,
            defaultLoadingItems: Int = 5,
            additionalParams: HashMap<Int, Any>? = null,
            customBindingMapping: ((itemBinding: ViewDataBinding, item: T) -> Unit)? = null
        ) {

            this.loading = UniversalAdapterViewType.Loading<T>(
                resourceLoading,
                defaultLoadingItems,
                additionalParams,
                customBindingMapping
            )
        }

        fun setLoadingFooter(loadingFooter: UniversalAdapterViewType.LoadingFooter<T>) {
            this.loadingFooter = loadingFooter
        }

        @JvmOverloads
        fun setLoadingFooter(
            @LayoutRes
            loaderFooter: Int? = null,
            additionalParams: HashMap<Int, Any>? = null,
            customBindingMapping: ((itemBinding: ViewDataBinding, item: T) -> Unit)? = null
        ) {
            this.loadingFooter = UniversalAdapterViewType.LoadingFooter<T>(
                loaderFooter,
                additionalParams,
                customBindingMapping
            )
        }

        fun setNoData(noData: UniversalAdapterViewType.NoData<T>) {
            this.noData = noData
        }

        @JvmOverloads
        fun setNoData(
            @LayoutRes
            noDataLayout: Int? = null,
            listener: Any? = null,
            additionalParams: HashMap<Int, Any>? = null,
            customBindingMapping: ((itemBinding: ViewDataBinding, item: String?) -> Unit)? = null
        ) {
            this.noData = UniversalAdapterViewType.NoData<T>(
                noDataLayout,
                listener,
                additionalParams,
                customBindingMapping
            )
        }

        fun setError(error: UniversalAdapterViewType.Error<T>) {
            this.error = error
        }

        @JvmOverloads
        fun setError(
            @LayoutRes
            errorLayout: Int? = null,
            listener: Any? = null,
            additionalParams: HashMap<Int, Any>? = null,
            customBindingMapping: ((itemBinding: ViewDataBinding, message: String?) -> Unit)? = null
        ) {
            this.error = UniversalAdapterViewType.Error<T>(
                errorLayout,
                listener,
                additionalParams,
                customBindingMapping
            )
        }


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
