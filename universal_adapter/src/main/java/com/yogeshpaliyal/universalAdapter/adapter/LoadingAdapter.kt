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
class LoadingAdapter<T>(
    val lifecycleOwner: LifecycleOwner?,
    var options: UniversalAdapterViewType.Loading<T>
) :
    ListAdapter<T, LoadingAdapter<T>.ViewHolder>(AsyncDifferConfig.Builder<T>(object :
        DiffUtil.ItemCallback<T>() {
        override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
            return false
        }

        override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
            return false
        }

    }).build()) {


    inner class ViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            binding.lifecycleOwner = lifecycleOwner
            binding.setVariable(BR.binding, binding)

            options.additionalParams?.forEach {
                binding.setVariable(it.key, it.value)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context),
            options.resourceLoading!!,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemViewType(position: Int): Int {
        return options.resourceLoading ?: 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int = options.defaultLoadingItems
}