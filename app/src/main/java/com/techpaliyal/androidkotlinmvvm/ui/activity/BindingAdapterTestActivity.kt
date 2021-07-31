package com.techpaliyal.androidkotlinmvvm.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.techpaliyal.androidkotlinmvvm.R
import com.techpaliyal.androidkotlinmvvm.databinding.ActivityBindingAdapterTestBinding
import com.techpaliyal.androidkotlinmvvm.ui.view_model.BindingTestViewModel
import com.techpaliyal.androidkotlinmvvm.ui.view_model.initViewModel

class BindingAdapterTestActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBindingAdapterTestBinding

    private val mViewModel by lazy {
        initViewModel(BindingTestViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBindingAdapterTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.lifecycleOwner = this
        binding.myLifecycleOwner = this
        binding.mViewModel =  mViewModel
        binding.executePendingBindings()

    }
}