package com.yogeshpaliyal.universalAdapter.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.yogeshpaliyal.universalAdapter.BR


/*
* @author Yogesh Paliyal
* yogeshpaliyal.foss@gmail.com
* https://techpaliyal.com
* created on 02-05-2021 19:57
*/
class NoDataAdapter<T>(
    val lifecycleOwner: LifecycleOwner?,
    var options : UniversalAdapterViewType.NoData<T>,
    var message: String = ""
) :
    ListAdapter<T, NoDataAdapter<T>.ViewHolder>(AsyncDifferConfig.Builder<T>(object :
        DiffUtil.ItemCallback<T>() {
        override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
            return false
        }

        override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
            return false
        }

    }).build()) {

    fun updateData(message: String?){
        this.message = message ?: ""
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return options.noDataLayout ?: 0
    }

    inner class ViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            binding.lifecycleOwner = lifecycleOwner

            if (options.customBindingMapping == null) {
                binding.setVariable(BR.message, message)
                binding.setVariable(BR.listener, options.listener)
                binding.setVariable(BR.binding, binding)

                options.additionalParams?.forEach {
                    binding.setVariable(it.key, it.value)
                }
                binding.executePendingBindings()
            } else {

                options.customBindingMapping?.invoke(
                    binding,
                    message
                )
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context),
            options.noDataLayout!!,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int = 1
}