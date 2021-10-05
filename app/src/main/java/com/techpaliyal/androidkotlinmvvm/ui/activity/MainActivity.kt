package com.techpaliyal.androidkotlinmvvm.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.techpaliyal.androidkotlinmvvm.R
import com.techpaliyal.androidkotlinmvvm.databinding.ActivityMainBinding
import com.techpaliyal.androidkotlinmvvm.ui.multiple_view.MultipleViewTypeActivity


/**
 * @author Yogesh Paliyal
 * yogeshpaliyal.foss@gmail.com
 * https://techpaliyal.com
 * https://yogeshpaliyal.com
 * Created Date : 9 January 2020
 */
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

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

        binding.btnBindingAdapter.setOnClickListener {
            val intent = Intent(this, BindingAdapterTestActivity::class.java)
            startActivity(intent)
        }

        binding.btnMultipleViews.setOnClickListener {
            SelectMultipleViewTypeExampleActivity.start(this)
        }

        binding.btnPaginationListing.setOnClickListener {
            PaginationListingActivity.start(this)
        }

    }
}
