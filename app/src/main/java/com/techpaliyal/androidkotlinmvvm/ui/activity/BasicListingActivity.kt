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
import com.techpaliyal.androidkotlinmvvm.model.BasicModel
import com.techpaliyal.androidkotlinmvvm.ui.view_model.BasicListingActivityViewModel
import com.techpaliyal.androidkotlinmvvm.ui.view_model.initViewModel
import com.yogeshpaliyal.universalAdapter.adapter.UniversalAdapterViewType
import com.yogeshpaliyal.universalAdapter.adapter.UniversalRecyclerAdapter
import com.yogeshpaliyal.universalAdapter.utils.Resource

/**
 * @author Yogesh Paliyal
 * yogeshpaliyal.foss@gmail.com
 * https://techpaliyal.com
 * https://yogeshpaliyal.com
 * Created Date : 9 January 2020
 */
class BasicListingActivity : AppCompatActivity() {

    lateinit var binding: ActivityListingBinding

    private val mViewModel by lazy {
        initViewModel(BasicListingActivityViewModel::class.java)
    }

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, BasicListingActivity::class.java)
            context.startActivity(intent)
        }
    }

    private val mAdapter by lazy {
        UniversalRecyclerAdapter.Builder<BasicModel>(
            lifecycleOwner = this,
            content = UniversalAdapterViewType.Content(
                R.layout.item_simple,
                listener = object : BasicListener<BasicModel> {
                    override fun onClick(model: BasicModel) {
                        Toast.makeText(this@BasicListingActivity, model.name, Toast.LENGTH_SHORT)
                            .show()
                    }
                })
        ).build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.adapter = mAdapter.getAdapter()

        mViewModel.data.observe(this, Observer {
            mAdapter.updateData(Resource.success(it))
        })

        mViewModel.fetchData()
    }
}
