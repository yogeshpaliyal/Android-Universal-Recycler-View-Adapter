package com.techpaliyal.androidkotlinmvvm.ui.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.techpaliyal.androidkotlinmvvm.databinding.ActivityMultipleViewTypeBinding
import com.techpaliyal.androidkotlinmvvm.databinding.ActivitySelectMultipleViewTypeExampleActivityBinding
import com.techpaliyal.androidkotlinmvvm.ui.multiple_view.MultipleViewTypeActivity
import com.techpaliyal.androidkotlinmvvm.ui.multiple_view_example_2.MultipleViewTypeChat
import com.techpaliyal.androidkotlinmvvm.ui.view_model.initViewModel
import com.yogeshpaliyal.universalAdapter.adapter.UniversalAdapterViewType
import com.yogeshpaliyal.universalAdapter.adapter.UniversalRecyclerAdapter

class SelectMultipleViewTypeExampleActivity : AppCompatActivity() {

    companion object{
        fun start(context: Context?) = context?.startActivity(Intent(context, SelectMultipleViewTypeExampleActivity::class.java))
    }

    lateinit var binding: ActivitySelectMultipleViewTypeExampleActivityBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectMultipleViewTypeExampleActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnChatListing.setOnClickListener {
            MultipleViewTypeChat.start(this)
        }

        binding.btnSchoolListing.setOnClickListener {
            MultipleViewTypeActivity.start(this)
        }

    }
}