package com.techpaliyal.androidkotlinmvvm.listeners


/**
 * @author Yogesh Paliyal
 * Created Date : 9 January 2020
 */
interface BasicListener<T>{
    fun onClick(model: T)
}