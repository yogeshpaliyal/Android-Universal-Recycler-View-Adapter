package com.techpaliyal.androidkotlinmvvm.ui.activity

import android.database.DatabaseUtils
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.techpaliyal.androidkotlinmvvm.R
import com.techpaliyal.androidkotlinmvvm.databinding.ActivityMainBinding
import com.techpaliyal.androidkotlinmvvm.listeners.BasicListener
import com.techpaliyal.androidkotlinmvvm.model.BasicModel
import com.techpaliyal.androidkotlinmvvm.ui.view_model.MainActivityViewModel

/**
 * @author Yogesh Paliyal
 * Created Date : 9 January 2020
 */
class MainActivity : AppCompatActivity(){

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)


        binding.lifecycleOwner = this
        val viewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        binding.viewModel = viewModel
        binding.executePendingBindings()

        binding.viewModel?.setData()
    }
}
