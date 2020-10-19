package com.techpaliyal.androidkotlinmvvm.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.techpaliyal.androidkotlinmvvm.R
import com.techpaliyal.androidkotlinmvvm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        binding.btnBasicListing.setOnClickListener {
            BasicListingActivity.start(this)
        }


        binding.btnUserListing.setOnClickListener {
            UserListingActivity.start(this)
        }

        binding.btnMultiSelect.setOnClickListener {
            MultiSelectListingActivity.start(this)
        }

        binding.btnLoadingListing.setOnClickListener {
            LoadingListingActivity.start(this)
        }

        binding.btnShimmerListing.setOnClickListener {
            ShimmerListingActivity.start(this)
        }

        binding.btnPaginationListing.setOnClickListener {
            PaginationListingActivity.start(this)
        }

    }
}
