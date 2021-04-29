package com.techpaliyal.androidkotlinmvvm.ui.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.techpaliyal.androidkotlinmvvm.model.UserModel
import com.yogeshpaliyal.universal_adapter.utils.Resource
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch


/**
 * @author Yogesh Paliyal
 * Created Date : 9 January 2020
 */
class LoadingListingViewModel : ViewModel() {
    val data = MutableLiveData<Resource<ArrayList<UserModel>>>()
    private var index = 1

    var fetchJob: Job? = null

    /**
     * Wait for 3 seconds then post the result
     */
    fun fetchData() {
        fetchJob = viewModelScope.launch {
            data.postValue(Resource.loading(data.value?.data))
            delay(3000)
            if (!isActive)
                return@launch

            val tempArr = data.value?.data ?: ArrayList()

            for (i in 0 until 10) {
                tempArr.add(
                    UserModel(
                        name = "Yogesh ${index++}",
                        image = "https://randomuser.me/api/portraits/men/52.jpg",
                        address = "Jodhpur"
                    )
                )
                tempArr.add(
                    UserModel(
                        name = "Umesh ${index++}",
                        image = "https://randomuser.me/api/portraits/men/62.jpg"
                    )
                )
                tempArr.add(
                    UserModel(
                        name = "Sohan ${index++}",
                        image = "https://randomuser.me/api/portraits/men/84.jpg"
                    )
                )
                tempArr.add(
                    UserModel(
                        name = "Pramod ${index++}",
                        image = "https://randomuser.me/api/portraits/men/83.jpg"
                    )
                )
            }
            if (isActive)
                data.postValue(Resource.success(tempArr))
        }

    }

}