package com.yogeshpaliyal.universal_adapter.utils

import androidx.recyclerview.widget.DiffUtil
import com.yogeshpaliyal.universal_adapter.adapter.UniversalRecyclerAdapter
import com.yogeshpaliyal.universal_adapter.model.BaseDiffUtil


/*
* @author Yogesh Paliyal
* techpaliyal@gmail.com
* https://techpaliyal.com
* created on 20-10-2020 21:56
*/

class UniversalDiffUtil<T : BaseDiffUtil>(val adapter : UniversalRecyclerAdapter<T>, val oldList : Resource<List<T>?>?, val newList : Resource<List<T>?>?) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        LogHelper.logD("TestingCrash","areItemsTheSame Start ")
        val result = if (oldItemPosition in 0 until (oldList?.data?.size ?:0) && newItemPosition in 0 until (newList?.data?.size ?:0)){
            oldList?.data?.get(oldItemPosition)?.getDiffId() == newList?.data?.get(newItemPosition)?.getDiffId()
        }else {
            false
        }

        LogHelper.logD("TestingCrash","areItemsTheSame Old Position => $oldItemPosition " +
                "&& New Position => $newItemPosition  Result => $result" +
                " Old List Size => $oldListSize  New List Size => $newListSize")


        return result
    }

    override fun getOldListSize(): Int = adapter.getSize(oldList)

    override fun getNewListSize(): Int = adapter.getSize(newList)

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val result =  if (oldItemPosition in 0..oldListSize && newItemPosition in 0..newItemPosition) {
            oldList?.data?.get(oldItemPosition)?.getDiffBody() == newList?.data?.get(newItemPosition)
                ?.getDiffBody()
        }else{
            false
        }


        LogHelper.logD("TestingCrash","areContentsTheSame Old Position => $oldItemPosition && New Position => $newItemPosition  Result => $result")
        return result
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        return super.getChangePayload(oldItemPosition, newItemPosition)
    }

}