package com.techpaliyal.androidkotlinmvvm.model

import com.techpaliyal.androidkotlinmvvm.utils.getRandomString


/*
* @author Yogesh Paliyal
* techpaliyal@gmail.com
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