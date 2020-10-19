package com.techpaliyal.androidkotlinmvvm.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.techpaliyal.androidkotlinmvvm.BR
import com.techpaliyal.androidkotlinmvvm.data.Resource
import com.techpaliyal.androidkotlinmvvm.data.Status
import com.techpaliyal.androidkotlinmvvm.utils.LogHelper


/**
 * @author Yogesh Paliyal
 * Created Date : 15 October 2020
 */
class UniversalRecyclerAdapter<T>(
    @LayoutRes val resource: Int,
    @LayoutRes val resourceShimmer: Int? = null,
    val defaultShimmerItems: Int = 5,
    @LayoutRes val loaderFooter: Int? = null,
    private var data: Resource<List<T>?>?= null,
    private var mListener: Any? = null
) : RecyclerView.Adapter<RecyclerView.ViewHolder?>() {

    private val VIEW_TYPE_LOADING = 0
    private val VIEW_TYPE_LOAD_MORE = 2
    private val VIEW_TYPE_NORMAL = 1

    fun updateData(data: Resource<List<T>?>) {
        this.data = data
        notifyDataSetChanged()

        LogHelper.logD("TestingCrash","updateData ${data.status} data => ${data.data?.size}")

        /*val diffCallback = UniversalDiffUtil(this.data.data, data.data)
        val diffResult = DiffUtil.calculateDiff(diffCallback, false)
        this.data = data
        diffResult.dispatchUpdatesTo(this)*/

    }

    fun getData() = data

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflator = LayoutInflater.from(parent.context)
        if (viewType == VIEW_TYPE_LOADING) {
            val binding = DataBindingUtil.inflate<ViewDataBinding>(
                layoutInflator,
                resourceShimmer!!,
                parent,
                false
            )
            return UnusedViewHolder(binding)
        } else if (viewType == VIEW_TYPE_LOAD_MORE) {
            val binding = DataBindingUtil.inflate<ViewDataBinding>(
                layoutInflator,
                loaderFooter!!,
                parent,
                false
            )
            return UnusedViewHolder(binding)
        } else {
            val binding =
                DataBindingUtil.inflate<ViewDataBinding>(layoutInflator, resource, parent, false)
            return MyViewHolder(binding)
        }

    }

    override fun getItemCount(): Int {
        return when (data?.status) {
            Status.LOADING -> if (data?.data?.isNullOrEmpty() != false && resourceShimmer != null) {
                defaultShimmerItems
            } else if (data?.data?.isNullOrEmpty() == false) {
                return (data?.data?.size ?: 0) + if (loaderFooter != null)   1 else 0
            } else {
                0
            }
            Status.SUCCESS -> data?.data?.size ?: 0
            else -> 0
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (data?.status == Status.LOADING && data?.data?.isNullOrEmpty() != false && resourceShimmer != null) {
            VIEW_TYPE_LOADING
        } else if (data?.status == Status.LOADING && data?.data?.isNullOrEmpty() == false && loaderFooter != null && position == itemCount - 1) {
            return VIEW_TYPE_LOAD_MORE
        } else {
            VIEW_TYPE_NORMAL
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is UniversalRecyclerAdapter<*>.MyViewHolder) {
            val item = data?.data?.get(position)
            if (item != null)
                holder.setupData(item)
        }
    }

    inner class MyViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
        fun setupData(model: Any) {
            binding.setVariable(BR.model, model)
            //binding.setVariable(BR.position, adapterPosition)
            binding.setVariable(BR.listener, mListener)
            binding.executePendingBindings()
        }
    }

    inner class UnusedViewHolder(binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root)

}