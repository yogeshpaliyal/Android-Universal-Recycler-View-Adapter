package com.techpaliyal.androidkotlinmvvm.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.techpaliyal.androidkotlinmvvm.R
import com.techpaliyal.androidkotlinmvvm.databinding.ActivityListingBinding
import com.techpaliyal.androidkotlinmvvm.listeners.BasicListener
import com.techpaliyal.androidkotlinmvvm.model.MultiSelectModel
import com.techpaliyal.androidkotlinmvvm.ui.view_model.MultiSelectListingActivityViewModel
import com.techpaliyal.androidkotlinmvvm.ui.view_model.initViewModel
import com.yogeshpaliyal.universal_adapter.adapter.UniversalRecyclerAdapter
import com.yogeshpaliyal.universal_adapter.utils.Resource

/**
 * @author Yogesh Paliyal
 * Created Date : 9 January 2020
 */
class MultiSelectListingActivity : AppCompatActivity() {

    lateinit var binding: ActivityListingBinding


    companion object {
        fun start(context: Context) {
            val intent = Intent(context, MultiSelectListingActivity::class.java)
            context.startActivity(intent)
        }
    }

    private val mViewModel by lazy {
        initViewModel(MultiSelectListingActivityViewModel::class.java)
    }

    private val mAdapter by lazy {
        UniversalRecyclerAdapter<MultiSelectModel>(
            R.layout.item_multi_select,
            mListener = object : BasicListener<MultiSelectModel> {
                override fun onClick(model: MultiSelectModel) {
                    mViewModel.logData()
                }
            })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.adapter = mAdapter

        mViewModel.data.observe(this, Observer {
            mAdapter.updateData(Resource.success(it))
        })

        mViewModel.fetchData()

    }
}
