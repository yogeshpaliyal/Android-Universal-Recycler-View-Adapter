package com.techpaliyal.androidkotlinmvvm.ui.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import com.techpaliyal.androidkotlinmvvm.R
import com.techpaliyal.androidkotlinmvvm.databinding.ActivityListingBinding
import com.techpaliyal.androidkotlinmvvm.listeners.BasicListener
import com.techpaliyal.androidkotlinmvvm.model.BasicModel
import com.techpaliyal.androidkotlinmvvm.ui.view_model.BasicListingActivityViewModel
import com.techpaliyal.androidkotlinmvvm.ui.view_model.initViewModel
import com.yogeshpaliyal.universal_adapter.adapter.UniversalRecyclerAdapter
import com.yogeshpaliyal.universal_adapter.utils.Resource

/**
 * @author Yogesh Paliyal
 * Created Date : 9 January 2020
 */
class BasicListingActivity : AppCompatActivity(){

    lateinit var binding: ActivityListingBinding

    private val mViewModel by lazy {
        initViewModel(BasicListingActivityViewModel::class.java)
    }

    companion object{
        fun start(context: Context){
            val intent = Intent(context, BasicListingActivity::class.java)
            context.startActivity(intent)
        }
    }

    private val mAdapter by lazy {
        UniversalRecyclerAdapter<BasicModel>(
            R.layout.item_simple,
            mListener = object : BasicListener<BasicModel> {
                override fun onClick(model: BasicModel) {
                    Toast.makeText(this@BasicListingActivity, model.name, Toast.LENGTH_SHORT).show()
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
