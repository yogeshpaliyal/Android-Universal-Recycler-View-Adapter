package com.yogeshpaliyal.universal_adapter.extensions

import androidx.annotation.LayoutRes
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.yogeshpaliyal.universal_adapter.adapter.UniversalAdapterViewType
import com.yogeshpaliyal.universal_adapter.adapter.UniversalRecyclerAdapter
import com.yogeshpaliyal.universal_adapter.utils.Resource
import java.util.*


@BindingAdapter("recycler_adapter")
fun RecyclerView.setRecyclerAdapter(adapter: RecyclerView.Adapter<*>) {
    this.adapter = adapter
}


@BindingAdapter(value = ["lifecycleOwner", "data", "item_layout", "loading_layout_count","loading_layout", "error_layout", "load_more_layout", "no_data_layout", "item_listener", "error_listener", "no_data_listener"], requireAll = false)
fun <T> RecyclerView.setRecyclerAdapter(
    lifecycleOwner: LifecycleOwner?,
    data: Resource<List<T>>?,
    @LayoutRes itemLayout: Int?,
    loadingLayoutCount: Int?,
    @LayoutRes loadingLayout: Int?,
    @LayoutRes errorLayout: Int?,
    @LayoutRes loadMoreLayout: Int?,
    @LayoutRes noDataLayout: Int?,
    itemListener: Any?,
    errorListener: Any?,
    noDataListener: Any?
) {
    if (itemLayout != null) {
        val tempAdapter = UniversalRecyclerAdapter.Builder(
            lifecycleOwner = lifecycleOwner, data = data,
            content = UniversalAdapterViewType.Content(itemLayout, itemListener),
            loading = UniversalAdapterViewType.Loading(loadingLayout,loadingLayoutCount),
            loadingFooter = UniversalAdapterViewType.LoadingFooter(loadMoreLayout),
            error = UniversalAdapterViewType.Error(errorLayout, errorListener),
            noData = UniversalAdapterViewType.NoData(noDataLayout, noDataListener)
        ).build()
        adapter = tempAdapter.getAdapter()
    }
}
