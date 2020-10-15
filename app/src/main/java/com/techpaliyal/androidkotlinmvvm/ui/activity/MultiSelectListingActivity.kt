package com.techpaliyal.androidkotlinmvvm.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.techpaliyal.androidkotlinmvvm.R
import com.techpaliyal.androidkotlinmvvm.data.Resource
import com.techpaliyal.androidkotlinmvvm.databinding.ActivityMultiSelectListingBinding
import com.techpaliyal.androidkotlinmvvm.model.MultiSelectModel
import com.techpaliyal.androidkotlinmvvm.ui.adapter.UniversalAdapter
import com.techpaliyal.androidkotlinmvvm.ui.view_model.MultiSelectListingActivityViewModel

/**
 * @author Yogesh Paliyal
 * Created Date : 9 January 2020
 */
class MultiSelectListingActivity : AppCompatActivity() {

    lateinit var binding: ActivityMultiSelectListingBinding


    companion object {
        fun start(context: Context) {
            val intent = Intent(context, MultiSelectListingActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_multi_select_listing)


        val viewModel =
            ViewModelProviders.of(this).get(MultiSelectListingActivityViewModel::class.java)
        viewModel.setData()

        binding.recyclerView.adapter = UniversalAdapter<MultiSelectModel>(
            R.layout.item_multi_select,
            data = Resource.success(viewModel.data.value),
            mListener = viewModel.basicListener
        )

    }
}
