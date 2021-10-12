package com.techpaliyal.androidkotlinmvvm.model

import com.yogeshpaliyal.universalAdapter.model.BaseDiffUtil


/**
 * @author Yogesh Paliyal
 * yogeshpaliyal.foss@gmail.com
 * https://techpaliyal.com
 * https://yogeshpaliyal.com
 * Created Date : 9 January 2020
 */
data class UserModel(var name: String, var address: String = "", var image: String, var isLiked: Boolean = false) : BaseDiffUtil {
    override fun getDiffId(): Any? {
        return name
    }

    override fun getDiffBody(): Any? {
        return "$name $address $image"
    }
}