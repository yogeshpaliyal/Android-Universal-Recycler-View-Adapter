package com.techpaliyal.androidkotlinmvvm.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import com.techpaliyal.androidkotlinmvvm.R
import com.techpaliyal.androidkotlinmvvm.databinding.ActivityListingBinding
import com.techpaliyal.androidkotlinmvvm.listeners.UsersListener
import com.techpaliyal.androidkotlinmvvm.model.UserModel
import com.techpaliyal.androidkotlinmvvm.ui.view_model.UserListingActivityViewModel
import com.techpaliyal.androidkotlinmvvm.ui.view_model.initViewModel
import com.yogeshpaliyal.universalAdapter.adapter.UniversalRecyclerAdapter
import com.yogeshpaliyal.universalAdapter.utils.Resource

/**
 * @author Yogesh Paliyal
 * yogeshpaliyal.foss@gmail.com
 * https://techpaliyal.com
 * https://yogeshpaliyal.com
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
        UniversalRecyclerAdapter.Builder<UserModel>(
            R.layout.item_user,
            mListener = object : UsersListener {
                override fun onLikeClicked(binding: ViewDataBinding,model: UserModel) {
                    model.isLiked = !model.isLiked
                    binding.invalidateAll()
                }

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
            mAdapter.updateData(Resource.success(it.toList()))
        })

        mViewModel.setData()

    }
}
