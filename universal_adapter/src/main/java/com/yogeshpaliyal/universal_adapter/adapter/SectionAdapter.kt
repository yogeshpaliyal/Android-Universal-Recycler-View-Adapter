package com.yogeshpaliyal.universal_adapter.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.yogeshpaliyal.universal_adapter.BR
import com.yogeshpaliyal.universal_adapter.model.BaseDiffUtil

class SectionAdapter<T, B : ViewDataBinding>(
    val lifecycleOwner: LifecycleOwner?,var model: T,val resource: Int,val customBindingMapping: ((itemBinding: B, item: T) -> Unit)
) :
    ListAdapter<T, SectionAdapter<T,B>.ViewHolder>(AsyncDifferConfig.Builder(object :
        DiffUtil.ItemCallback<T>() {
        override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
            return if (oldItem is BaseDiffUtil && newItem is BaseDiffUtil) {
                oldItem.getDiffId() == newItem.getDiffId()
            } else {
                oldItem.hashCode() == newItem.hashCode()
            }
        }

        override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
            return if (oldItem is BaseDiffUtil && newItem is BaseDiffUtil) {
                oldItem.getDiffBody() == newItem.getDiffBody()
            } else {
                oldItem.hashCode() == newItem.hashCode()
            }
        }

    }).build()) {


    inner class ViewHolder(val binding: B) : RecyclerView.ViewHolder(binding.root) {
        fun bind(model: T) {
            binding.lifecycleOwner = lifecycleOwner
                customBindingMapping.invoke(
                    binding,
                    model
                )

        }
    }

    override fun submitList(list: List<T>?) {
        super.submitList(list?.let { ArrayList(it) })
    }

    override fun getItemViewType(position: Int): Int {
        return resource
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<B>(
            LayoutInflater.from(parent.context),
            resource,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}