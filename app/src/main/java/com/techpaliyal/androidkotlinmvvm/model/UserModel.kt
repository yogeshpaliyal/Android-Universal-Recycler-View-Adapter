package com.techpaliyal.androidkotlinmvvm.model

data class UserModel(var name: String,var address: String = "",var image: String) : BaseDiffUtil{
    override fun getDiffId(): Any? {
        return name
    }

    override fun getDiffBody(): Any? {
        return "$name $address $image"
    }
}