package com.techpaliyal.androidkotlinmvvm.model

import com.yogeshpaliyal.universal_adapter.model.BaseDiffUtil

data class UserModel(var name: String,var address: String = "",var image: String) : BaseDiffUtil {
    override fun getDiffId(): Any? {
        return name
    }

    override fun getDiffBody(): Any? {
        return "$name $address $image"
    }
}