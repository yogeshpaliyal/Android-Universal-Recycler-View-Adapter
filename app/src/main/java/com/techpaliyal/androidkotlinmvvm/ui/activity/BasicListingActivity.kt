package com.techpaliyal.androidkotlinmvvm.ui.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.techpaliyal.androidkotlinmvvm.R
import com.techpaliyal.androidkotlinmvvm.data.Resource
import com.techpaliyal.androidkotlinmvvm.databinding.ActivityBasicListingBinding
import com.techpaliyal.androidkotlinmvvm.model.BasicModel
import com.techpaliyal.androidkotlinmvvm.ui.adapter.UniversalAdapter
import com.techpaliyal.androidkotlinmvvm.ui.view_model.BasicListingActivityViewModel

/**
 * @author Yogesh Paliyal
 * Created Date : 9 January 2020
 */
class BasicListingActivity : AppCompatActivity(){

    lateinit var binding: ActivityBasicListingBinding


    companion object{
        fun start(context: Context){
            val intent = Intent(context, BasicListingActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_basic_listing)

        val viewModel = ViewModelProviders.of(this).get(BasicListingActivityViewModel::class.java)
        viewModel.setData()

        binding.recyclerView.adapter = UniversalAdapter<BasicModel>(R.layout.item_simple,data = Resource.success(viewModel.data.value), mListener = viewModel.basicListener)
    }
}
