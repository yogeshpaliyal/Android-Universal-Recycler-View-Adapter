package com.techpaliyal.androidkotlinmvvm.ui.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.techpaliyal.androidkotlinmvvm.R
import com.techpaliyal.androidkotlinmvvm.databinding.ActivityBasicListingBinding
import com.techpaliyal.androidkotlinmvvm.databinding.ActivityUserListingBinding
import com.techpaliyal.androidkotlinmvvm.ui.view_model.BasicListingActivityViewModel
import com.techpaliyal.androidkotlinmvvm.ui.view_model.UserListingActivityViewModel

/**
 * @author Yogesh Paliyal
 * Created Date : 9 January 2020
 */
class UserListingActivity : AppCompatActivity(){

    lateinit var binding: ActivityUserListingBinding

    companion object{
        fun start(context: Context){
            val intent = Intent(context, UserListingActivity::class.java)
            context.startActivity(intent)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_user_listing)


        binding.lifecycleOwner = this
        val viewModel = ViewModelProviders.of(this).get(UserListingActivityViewModel::class.java)
        binding.viewModel = viewModel
        binding.executePendingBindings()

        binding.viewModel?.setData()
    }
}
