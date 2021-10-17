package com.yogeshpaliyal.universalAdapter.extensions

import androidx.annotation.LayoutRes
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.yogeshpaliyal.universalAdapter.adapter.UniversalAdapterViewType
import com.yogeshpaliyal.universalAdapter.adapter.UniversalRecyclerAdapter
import com.yogeshpaliyal.universalAdapter.utils.DEFAULT_LOADING_ITEMS
import com.yogeshpaliyal.universalAdapter.utils.Resource


@BindingAdapter("recycler_adapter")
fun RecyclerView.setRecyclerAdapter(adapter: RecyclerView.Adapter<*>) {
    if (this.adapter != adapter)
        this.adapter = adapter
}


@BindingAdapter(
    value = ["lifecycleOwner",
        "data",
        "item_layout",
        "loading_layout_count",
        "loading_layout",
        "error_layout",
        "load_more_layout",
        "no_data_layout",
        "item_listener",
        "error_listener",
        "no_data_listener",
        "ura_use_tag"], // Allow universal recycler view to set tag
    requireAll = false
)
@Suppress("UNCHECKED_CAST")
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
    noDataListener: Any?,
    useTag: Boolean? = true // use
) {
    if (itemLayout != null) {
        val universalAdapter = tag as? UniversalRecyclerAdapter<T>
        if(useTag != false && universalAdapter != null){
            data?.let {
                universalAdapter.updateData(it)
            }
        }else {
            val builder = UniversalRecyclerAdapter.Builder<T>()
            builder.setLifecycleOwner(lifecycleOwner)

            builder.setData(data)

            builder.setContent(itemLayout, itemListener)
            if(loadingLayout != null)
                builder.setLoading(loadingLayout,loadingLayoutCount ?: DEFAULT_LOADING_ITEMS)
            if(loadMoreLayout != null)
                builder.setLoadingFooter(loadMoreLayout)

            if(errorLayout != null)
                builder.setError(errorLayout, errorListener)
            if(noDataLayout != null)
                builder.setNoData(noDataLayout, noDataListener)

            val tempAdapter = builder.build()
            adapter = tempAdapter.getAdapter()
            if (useTag != false)
                tag = tempAdapter
        }
    }
}
