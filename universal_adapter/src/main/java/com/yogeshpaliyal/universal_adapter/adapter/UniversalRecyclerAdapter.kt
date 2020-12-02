package com.yogeshpaliyal.universal_adapter.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.yogeshpaliyal.universal_adapter.BR
import com.yogeshpaliyal.universal_adapter.model.BaseDiffUtil
import com.yogeshpaliyal.universal_adapter.utils.LogHelper
import com.yogeshpaliyal.universal_adapter.utils.Resource
import com.yogeshpaliyal.universal_adapter.utils.Status
import com.yogeshpaliyal.universal_adapter.utils.UniversalDiffUtil


/**
 * @author Yogesh Paliyal
 * Created Date : 15 October 2020
 */
class UniversalRecyclerAdapter<T: BaseDiffUtil>(
    @LayoutRes val resource: Int,
    @LayoutRes val resourceLoading: Int? = null,
    val defaultLoadingItems: Int = 5,
    @LayoutRes val loaderFooter: Int? = null,
    private var data: Resource<ArrayList<T>?>?= null,
    @LayoutRes val errorLayout: Int? = null,
    private var errorListener: Any? = null,
    private var mListener: Any? = null,
    @LayoutRes val noDataLayout: Int? = null,
    private var noDataListener: Any? = null ) : RecyclerView.Adapter<RecyclerView.ViewHolder?>() {

    private val VIEW_TYPE_LOADING = 0
    private val VIEW_TYPE_NORMAL = 1
    private val VIEW_TYPE_LOAD_MORE = 2
    private val VIEW_TYPE_ERROR = 3
    private val VIEW_NO_DATA = 4


    fun updateData(data: Resource<ArrayList<T>?>) {

        LogHelper.logD("TestingCrash","updateData ${data.status} data => ${data.data?.size}")

        val diffCallback = UniversalDiffUtil(this,this.data, data)
        val diffResult = DiffUtil.calculateDiff(diffCallback, false)

        this.data = Resource.create(data.status,data.data,data.message)

        diffResult.dispatchUpdatesTo(this)
    }

    fun getData() = data

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflator = LayoutInflater.from(parent.context)
        if (viewType == VIEW_TYPE_LOADING) {
            val binding = DataBindingUtil.inflate<ViewDataBinding>(
                layoutInflator,
                resourceLoading!!,
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
        } else if(viewType == VIEW_TYPE_ERROR) {
            val binding =
                DataBindingUtil.inflate<ViewDataBinding>(layoutInflator, errorLayout!!, parent, false)
            return ErrorViewHolder(binding)
        } else if (viewType == VIEW_NO_DATA) {
            val binding =
                DataBindingUtil.inflate<ViewDataBinding>(
                    layoutInflator,
                    noDataLayout!!,
                    parent,
                    false
                )
            return NoDataViewHolder(binding)
        }else {
            val binding =
                DataBindingUtil.inflate<ViewDataBinding>(layoutInflator, resource, parent, false)
            return MyViewHolder(binding)
        }

    }


    inner class NoDataViewHolder(val binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setupData() {
            binding.setVariable(BR.message, data?.message ?: "")
            binding.setVariable(BR.listener, noDataListener)
            binding.executePendingBindings()
        }
    }

    override fun getItemCount(): Int {
        return getSize(data)
    }

    fun getSize(listData : Resource<List<T>?>?): Int{
        return when (listData?.status) {
            Status.LOADING -> if (listData.data?.isNullOrEmpty() != false && resourceLoading != null) {
                defaultLoadingItems
            } else if (listData.data?.isNullOrEmpty() == false) {
                return (listData.data.size ?: 0) + if (loaderFooter != null) 1 else 0
            } else {
                0
            }
            Status.SUCCESS -> {
                val size = listData.data?.size ?: 0
                return if (size == 0 && noDataLayout != null) 1 else size
            }
            Status.ERROR -> (listData.data?.size ?: 0) + if (errorLayout != null) 1 else 0
            else -> 0
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (data?.status == Status.LOADING && data?.data?.isNullOrEmpty() != false && resourceLoading != null) {
            VIEW_TYPE_LOADING
        } else if (data?.status == Status.LOADING && data?.data?.isNullOrEmpty() == false && loaderFooter != null && position == itemCount - 1) {
            return VIEW_TYPE_LOAD_MORE
        } else if(data?.status == Status.ERROR && errorLayout != null && position == itemCount - 1){
            return VIEW_TYPE_ERROR
        } else {
            if (data?.data?.isNullOrEmpty() == true && noDataLayout != null) {
                VIEW_NO_DATA
            } else {
                VIEW_TYPE_NORMAL
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is UniversalRecyclerAdapter<*>.MyViewHolder) {
            val item = data?.data?.get(position)
            if (item != null)
                holder.setupData(item)
        }else if (holder is UniversalRecyclerAdapter<*>.ErrorViewHolder) {
            holder.setupData()
        }else if (holder is UniversalRecyclerAdapter<*>.NoDataViewHolder) {
            holder.setupData()
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


    inner class ErrorViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
        fun setupData() {
            binding.setVariable(BR.message, data?.message ?: "")
            binding.setVariable(BR.listener, errorListener)
            binding.executePendingBindings()
        }
    }

    inner class UnusedViewHolder(binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root)

}