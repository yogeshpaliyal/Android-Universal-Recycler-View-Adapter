package com.techpaliyal.androidkotlinmvvm.model

import com.yogeshpaliyal.universalAdapter.model.BaseDiffUtil


/**
 * @author Yogesh Paliyal
 * yogeshpaliyal.foss@gmail.com
 * https://techpaliyal.com
 * https://yogeshpaliyal.com
 * Created Date : 9 January 2020
 */
data class MultiSelectModel(val name: String, var isChecked: Boolean) : BaseDiffUtil