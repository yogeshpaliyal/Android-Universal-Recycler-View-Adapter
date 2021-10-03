package com.techpaliyal.androidkotlinmvvm.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import com.techpaliyal.androidkotlinmvvm.R
import com.techpaliyal.androidkotlinmvvm.databinding.ActivityListingBinding
import com.techpaliyal.androidkotlinmvvm.extensions.setupPagination
import com.techpaliyal.androidkotlinmvvm.listeners.BasicListener
import com.techpaliyal.androidkotlinmvvm.listeners.UsersListener
import com.techpaliyal.androidkotlinmvvm.model.UserModel
import com.techpaliyal.androidkotlinmvvm.ui.view_model.LoadingListingViewModel
import com.techpaliyal.androidkotlinmvvm.ui.view_model.initViewModel
import com.yogeshpaliyal.universal_adapter.adapter.UniversalRecyclerAdapter

/**
 * @author Yogesh Paliyal
 * yogeshpaliyal.foss@gmail.com
 * https://techpaliyal.com
 * https://yogeshpaliyal.com
 * Created Date : 9 January 2020
 */
class PaginationListingActivity : AppCompatActivity() {
    lateinit var binding: ActivityListingBinding


    companion object {
        @JvmStatic
        fun start(context: Context) {
            val starter = Intent(context, PaginationListingActivity::class.java)
            context.startActivity(starter)
        }
    }

    private val mViewModel by lazy {
        initViewModel(LoadingListingViewModel::class.java)
    }

    private val mAdapter by lazy {
        UniversalRecyclerAdapter.Builder<UserModel>(
            R.layout.item_user,
            resourceLoading = R.layout.item_user_shimmer,
            defaultLoadingItems = 5,
            loaderFooter = R.layout.item_loading_more,
            mListener = object : UsersListener {
                override fun onLikeClicked(binding: ViewDataBinding, model: UserModel) {

                }

                override fun onClick(model: UserModel) {
                    Toast.makeText(this@PaginationListingActivity, model.name, Toast.LENGTH_SHORT)
                        .show()
                }
            }).build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.adapter = mAdapter.getAdapter()

        binding.recyclerView.setupPagination {
            if (mViewModel.fetchJob?.isActive == false)
                mViewModel.fetchData()
        }

        mViewModel.data.observe(this, Observer {
            mAdapter.updateData(it)

            /* when(it.status){
                 Status.LOADING -> {
                 }
                 Status.SUCCESS -> {
                     mAdapter.updateData(Resource.success(mViewModel.arrAllUsers))
                 }
             }*/
        })

        mViewModel.fetchData()
    }
}