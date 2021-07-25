package com.techpaliyal.androidkotlinmvvm.listeners

import androidx.databinding.ViewDataBinding
import com.techpaliyal.androidkotlinmvvm.model.UserModel

interface UsersListener {
    fun onLikeClicked(binding: ViewDataBinding,model: UserModel)
    fun onClick(model : UserModel)
}