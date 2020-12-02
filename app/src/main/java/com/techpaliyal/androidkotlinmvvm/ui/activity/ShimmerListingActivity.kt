package com.techpaliyal.androidkotlinmvvm.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.techpaliyal.androidkotlinmvvm.R
import com.techpaliyal.androidkotlinmvvm.databinding.ActivityListingBinding
import com.techpaliyal.androidkotlinmvvm.listeners.BasicListener
import com.techpaliyal.androidkotlinmvvm.model.UserModel
import com.techpaliyal.androidkotlinmvvm.ui.view_model.LoadingListingViewModel
import com.techpaliyal.androidkotlinmvvm.ui.view_model.initViewModel
import com.yogeshpaliyal.universal_adapter.adapter.UniversalRecyclerAdapter

class ShimmerListingActivity : AppCompatActivity() {
    lateinit var binding: ActivityListingBinding


    companion object {
        @JvmStatic
        fun start(context: Context) {
            val starter = Intent(context, ShimmerListingActivity::class.java)
            context.startActivity(starter)
        }
    }

    private val mViewModel by lazy {
        initViewModel(LoadingListingViewModel::class.java)
    }

    private val mAdapter by lazy {
        UniversalRecyclerAdapter<UserModel>(
            R.layout.item_user, resourceLoading = R.layout.item_user_shimmer,
            defaultLoadingItems = 5,
            mListener = object : BasicListener<UserModel> {
                override fun onClick(model: UserModel) {
                    Toast.makeText(this@ShimmerListingActivity, model.name, Toast.LENGTH_SHORT)
                        .show()
                }
            })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.adapter = mAdapter

        mViewModel.data.observe(this, Observer {
            mAdapter.updateData(it)
        })

        mViewModel.fetchData()
    }
}