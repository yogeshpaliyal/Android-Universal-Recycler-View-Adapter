package com.techpaliyal.androidkotlinmvvm.ui.multiple_view_example_2

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.techpaliyal.androidkotlinmvvm.databinding.ActivityMultipleViewTypeBinding
import com.techpaliyal.androidkotlinmvvm.databinding.ActivityMultipleViewTypeExample2Binding
import com.techpaliyal.androidkotlinmvvm.ui.multiple_view.MultipleViewTypeViewModel
import com.techpaliyal.androidkotlinmvvm.ui.multiple_view.SchoolListing
import com.techpaliyal.androidkotlinmvvm.ui.view_model.initViewModel
import com.yogeshpaliyal.universalAdapter.adapter.UniversalAdapterViewType
import com.yogeshpaliyal.universalAdapter.adapter.UniversalRecyclerAdapter

class MultipleViewTypeChat : AppCompatActivity() {

    companion object{
        fun start(context: Context?) = context?.startActivity(Intent(context, MultipleViewTypeChat::class.java))
    }

    lateinit var binding: ActivityMultipleViewTypeExample2Binding

    private val mViewModel by lazy {
        initViewModel(MultipleViewChatViewModel::class.java)
    }


    private val mAdapter by lazy {
        UniversalRecyclerAdapter.Builder<ChatListing>(lifecycleOwner = this,
            content = UniversalAdapterViewType.Content()).build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMultipleViewTypeExample2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerViewChat.adapter = mAdapter.getAdapter()

        mViewModel.sampleList.observe(this, Observer {
            mAdapter.updateData(it)
        })


    }
}