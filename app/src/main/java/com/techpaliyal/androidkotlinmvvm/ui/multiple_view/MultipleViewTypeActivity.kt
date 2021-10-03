package com.techpaliyal.androidkotlinmvvm.ui.multiple_view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.techpaliyal.androidkotlinmvvm.databinding.ActivityMultipleViewTypeBinding
import com.techpaliyal.androidkotlinmvvm.ui.view_model.initViewModel
import com.yogeshpaliyal.universalAdapter.adapter.UniversalAdapterViewType
import com.yogeshpaliyal.universalAdapter.adapter.UniversalRecyclerAdapter

class MultipleViewTypeActivity : AppCompatActivity() {

    companion object{
        fun start(context: Context?) = context?.startActivity(Intent(context, MultipleViewTypeActivity::class.java))
    }

    lateinit var binding: ActivityMultipleViewTypeBinding

    private val mViewModel by lazy {
        initViewModel(MultipleViewTypeViewModel::class.java)
    }


    private val mAdapter by lazy {
        UniversalRecyclerAdapter.Builder<SchoolListing>(lifecycleOwner = this,
        content = UniversalAdapterViewType.Content()).build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMultipleViewTypeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.adapter = mAdapter.getAdapter()

        mViewModel.sampleList.observe(this, Observer {
            mAdapter.updateData(it)
        })


    }
}