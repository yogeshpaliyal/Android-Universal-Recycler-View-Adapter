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


/*
* @author Yogesh Paliyal
* techpaliyal@gmail.com
* https://techpaliyal.com
* created on 02-05-2021 19:57
*/
class ContentListAdapter<T>(
    val lifecycleOwner: LifecycleOwner?,
    var resource: Int,
    val listener: Any?,
    val customBinding: ((itemBinding: ViewDataBinding, item: T, bindingAdapterPosition: Int) -> Unit)?
) :
    ListAdapter<T, ContentListAdapter<T>.ViewHolder>(AsyncDifferConfig.Builder(object :
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


    inner class ViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(model: T) {
            binding.lifecycleOwner = lifecycleOwner

            if (customBinding == null) {
                binding.setVariable(BR.model, model)
                binding.setVariable(BR.listener, listener)
                binding.executePendingBindings()
            } else {

                customBinding.invoke(
                    binding,
                    model,
                    bindingAdapterPosition
                )
            }

        }
    }

    override fun submitList(list: List<T>?) {
        super.submitList(list?.let { ArrayList(it) })
    }

    override fun getItemViewType(position: Int): Int {
        return resource
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
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