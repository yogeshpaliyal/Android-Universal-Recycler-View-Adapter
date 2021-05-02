package com.yogeshpaliyal.universal_adapter.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.yogeshpaliyal.universal_adapter.BR
import com.yogeshpaliyal.universal_adapter.utils.Resource
import com.yogeshpaliyal.universal_adapter.utils.Status
import com.yogeshpaliyal.universal_adapter.utils.UniversalAdapterBuilder
import com.yogeshpaliyal.universal_adapter.utils.UniversalDiffUtil


/**
 * @author Yogesh Paliyal
 * Created Date : 15 October 2020
 */
class UniversalRecyclerAdapter<T> constructor(val adapterOptions: UniversalAdapterBuilder<T>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder?>() {
//    var adapterOptions : UniversalAdapterOptions<T> = UniversalAdapterOptions(resource, resourceLoading, defaultLoadingItems, loaderFooter, data, errorLayout, errorListener, mListener, noDataLayout, noDataListener, lifecycleOwner)


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
                UniversalAdapterBuilder<T>(
                    lifecycleOwner,
                    data,
                    UniversalAdapterViewType.Content(resource, mListener),
                    UniversalAdapterViewType.Loading(resourceLoading, defaultLoadingItems),
                    UniversalAdapterViewType.LoadingFooter(loaderFooter),
                    UniversalAdapterViewType.NoData(noDataLayout, noDataListener),
                    UniversalAdapterViewType.Error(errorLayout, errorListener)
                )
            )


    fun updateData(data: Resource<List<T>?>) {

        val diffCallback = UniversalDiffUtil({
            getSize(it)
        }, adapterOptions.data, data)
        val diffResult = DiffUtil.calculateDiff(diffCallback, false)

        adapterOptions.data = data

        diffResult.dispatchUpdatesTo(this)
    }

    // fun getData() = adapterOptions.data

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflator = LayoutInflater.from(parent.context)
        if (viewType == adapterOptions.loading?.resourceLoading) {
            val binding = DataBindingUtil.inflate<ViewDataBinding>(
                layoutInflator,
                adapterOptions.loading.resourceLoading,
                parent,
                false
            )
            return UnusedViewHolder(binding)
        } else if (viewType == adapterOptions.loadingFooter?.loaderFooter) {
            val binding = DataBindingUtil.inflate<ViewDataBinding>(
                layoutInflator,
                adapterOptions.loadingFooter.loaderFooter,
                parent,
                false
            )
            return UnusedViewHolder(binding)
        } else if (viewType == adapterOptions.error?.errorLayout) {
            val binding =
                DataBindingUtil.inflate<ViewDataBinding>(
                    layoutInflator,
                    adapterOptions.error.errorLayout,
                    parent,
                    false
                )
            return ErrorViewHolder(binding)
        } else if (viewType == adapterOptions.noData?.noDataLayout) {
            val binding =
                DataBindingUtil.inflate<ViewDataBinding>(
                    layoutInflator,
                    adapterOptions.noData.noDataLayout,
                    parent,
                    false
                )
            return NoDataViewHolder(binding)
        } else {
            val binding =
                DataBindingUtil.inflate<ViewDataBinding>(
                    layoutInflator,
                    adapterOptions.content?.resource!!,
                    parent,
                    false
                )
            return MyViewHolder(binding)
        }

    }


    inner class NoDataViewHolder(val binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setupData() {
            binding.lifecycleOwner = adapterOptions.lifecycleOwner
            binding.setVariable(BR.message, adapterOptions.data?.message ?: "")
            binding.setVariable(BR.listener, adapterOptions.noData?.listener)
            binding.executePendingBindings()
        }
    }

    override fun getItemCount(): Int {
        return getSize(adapterOptions.data)
    }

    fun getSize(listData: Resource<List<T>?>?): Int {
        return when (listData?.status) {
            Status.LOADING -> if (listData.data?.isNullOrEmpty() != false && adapterOptions.loading?.resourceLoading != null) {
                adapterOptions.loading.defaultLoadingItems
            } else if (listData.data?.isNullOrEmpty() == false) {
                return listData.data.size + if (adapterOptions.loadingFooter?.loaderFooter != null) 1 else 0
            } else {
                0
            }
            Status.SUCCESS -> {
                val size = listData.data?.size ?: 0
                return if (size == 0 && adapterOptions.noData?.noDataLayout != null) 1 else size
            }
            Status.ERROR -> (listData.data?.size
                ?: 0) + if (adapterOptions.error?.errorLayout != null) 1 else 0
            else -> 0
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (adapterOptions.data?.status == Status.LOADING && adapterOptions.data?.data?.isNullOrEmpty() != false && adapterOptions.loading?.resourceLoading != null) {
            adapterOptions.loading.resourceLoading
        } else if (adapterOptions.data?.status == Status.LOADING && adapterOptions.data?.data?.isNullOrEmpty() == false && adapterOptions.loadingFooter?.loaderFooter != null && position == itemCount - 1) {
            return adapterOptions.loadingFooter.loaderFooter
        } else if (adapterOptions.data?.status == Status.ERROR && adapterOptions.error?.errorLayout != null && position == itemCount - 1) {
            return adapterOptions.error.errorLayout
        } else {
            if (adapterOptions.data?.data?.isNullOrEmpty() == true && adapterOptions.noData?.noDataLayout != null) {
                adapterOptions.noData.noDataLayout
            } else {
                adapterOptions.content?.resource ?: 0
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is UniversalRecyclerAdapter<*>.MyViewHolder) {
            holder.setupData(position)
        } else if (holder is UniversalRecyclerAdapter<*>.ErrorViewHolder) {
            holder.setupData()
        } else if (holder is UniversalRecyclerAdapter<*>.NoDataViewHolder) {
            holder.setupData()
        }
    }

    inner class MyViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
        fun setupData(holderPosition: Int) {
            binding.lifecycleOwner = adapterOptions.lifecycleOwner
            val model = adapterOptions.data?.data?.get(holderPosition)
            adapterOptions.content?.let { content ->
                if (content.customBindingMapping == null) {
                    binding.setVariable(BR.model, model)
                    binding.setVariable(BR.listener, content.listener ?: content.listener)
                    binding.executePendingBindings()
                } else {
                    model?.let {
                        content.customBindingMapping.invoke(
                            binding,
                            it,
                            bindingAdapterPosition
                        )
                    }
                }

            }
        }
    }


    inner class ErrorViewHolder(val binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setupData() {
            binding.lifecycleOwner = adapterOptions.lifecycleOwner
            adapterOptions.error?.let { error ->
                if (error.customBindingMapping == null) {
                    binding.setVariable(BR.message, adapterOptions.data?.message ?: "")
                    binding.setVariable(BR.listener, error.listener ?: error.listener)
                    binding.executePendingBindings()

                } else {
                    error.customBindingMapping.invoke(binding, adapterOptions.data?.message)
                }
            }

        }
    }

    inner class UnusedViewHolder(binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root)

}
