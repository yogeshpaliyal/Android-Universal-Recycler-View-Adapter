package com.yogeshpaliyal.universal_adapter.model

import com.yogeshpaliyal.universal_adapter.utils.getRandomString


/*
* @author Yogesh Paliyal
* yogeshpaliyal.foss@gmail.com
* https://techpaliyal.com
* created on 20-10-2020 21:57
*/
interface BaseDiffUtil {
    fun getDiffBody() : Any?{
        return getRandomString()
    }

    fun getDiffId() : Any? {
        return getRandomString()
    }
}