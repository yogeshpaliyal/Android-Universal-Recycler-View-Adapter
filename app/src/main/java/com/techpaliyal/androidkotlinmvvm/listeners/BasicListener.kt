package com.techpaliyal.androidkotlinmvvm.listeners


/**
 * @author Yogesh Paliyal
 * yogeshpaliyal.foss@gmail.com
 * https://techpaliyal.com
 * https://yogeshpaliyal.com
 * Created Date : 9 January 2020
 */
interface BasicListener<T>{
    fun onClick(model: T)
}