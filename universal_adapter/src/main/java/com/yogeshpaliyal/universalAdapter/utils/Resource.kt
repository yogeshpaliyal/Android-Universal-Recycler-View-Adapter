package com.yogeshpaliyal.universalAdapter.utils


/**
 * @author Yogesh Paliyal
 * Created Date : 15 October 2020
 */
data class Resource<out T>(val status: Status, val data: T?, val message: String?) {

    companion object {

        fun <T> success(data: T?, message: String?= ""): Resource<T> {
            return Resource(Status.SUCCESS, data, message)
        }

        fun <T> create(status: Status,data: T?, message: String?= ""): Resource<T> {
            return Resource(status, data, message)
        }


        fun <T> error(msg: String?, data: T? = null): Resource<T> {
            return Resource(Status.ERROR, data, msg)
        }

        fun <T> loading(data: T? = null): Resource<T> {
            return Resource(Status.LOADING, data, null)
        }

    }
}