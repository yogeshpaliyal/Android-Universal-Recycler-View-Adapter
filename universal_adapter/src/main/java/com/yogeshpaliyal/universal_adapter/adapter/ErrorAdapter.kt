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


/*
* @author Yogesh Paliyal
* techpaliyal@gmail.com
* https://techpaliyal.com
* created on 02-05-2021 19:57
*/
class ErrorAdapter<T>(
    val lifecycleOwner: LifecycleOwner?,
    var resource: Int,
    var message: String = "",
    val listener: Any?,
    val customBinding: ((itemBinding: ViewDataBinding, message: String?) -> Unit)?
) :
    ListAdapter<T, ErrorAdapter<T>.ViewHolder>(AsyncDifferConfig.Builder<T>(object :
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
        return resource
    }

    inner class ViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            binding.lifecycleOwner = lifecycleOwner

            if (customBinding == null) {
                binding.setVariable(BR.message, message)
                binding.setVariable(BR.listener, listener)
                binding.executePendingBindings()
            } else {

                customBinding.invoke(
                    binding,
                    message
                )
            }

        }
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
        holder.bind()
    }

    override fun getItemCount(): Int = 1
}