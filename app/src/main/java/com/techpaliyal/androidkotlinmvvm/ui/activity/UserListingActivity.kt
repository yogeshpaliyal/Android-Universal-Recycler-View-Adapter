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
import com.techpaliyal.androidkotlinmvvm.ui.view_model.UserListingActivityViewModel
import com.techpaliyal.androidkotlinmvvm.ui.view_model.initViewModel
import com.yogeshpaliyal.universal_adapter.utils.Resource
import com.yogeshpaliyal.universal_adapter.utils.UniversalAdapterBuilder

/**
 * @author Yogesh Paliyal
 * Created Date : 9 January 2020
 */
class UserListingActivity : AppCompatActivity() {

    lateinit var binding: ActivityListingBinding

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, UserListingActivity::class.java)
            context.startActivity(intent)
        }
    }
    private val mViewModel by lazy {
        initViewModel(UserListingActivityViewModel::class.java)
    }

    private val mAdapter by lazy {
        UniversalAdapterBuilder<UserModel>(
            R.layout.item_user,
            mListener = object : BasicListener<UserModel> {
                override fun onClick(model: UserModel) {
                    Toast.makeText(this@UserListingActivity, model.name, Toast.LENGTH_SHORT).show()
                }
            }).build()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.adapter = mAdapter.getAdapter()

        mViewModel.data.observe(this, Observer {
            mAdapter.updateData(Resource.success(it))
        })

        mViewModel.setData()

    }
}
