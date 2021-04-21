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
import com.yogeshpaliyal.universal_adapter.BR
import com.yogeshpaliyal.universal_adapter.adapter.SectionUniversalRecyclerAdapterBuilder
import com.yogeshpaliyal.universal_adapter.adapter.UniversalAdapterViewType
import com.yogeshpaliyal.universal_adapter.utils.Resource
import com.yogeshpaliyal.universal_adapter.utils.UniversalAdapterBuilder

/**
 * @author Yogesh Paliyal
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
        val contentOption = UniversalAdapterBuilder<BasicModel>(
            lifecycleOwner = this,
            content = UniversalAdapterViewType.Content(
                R.layout.item_simple,
                mListener = object : BasicListener<BasicModel> {
                    override fun onClick(model: BasicModel) {
                        Toast.makeText(this@BasicListingActivity, model.name, Toast.LENGTH_SHORT)
                            .show()
                    }
                },
                customBindingMapping = { itemBinding, item, position ->
                    itemBinding.setVariable(BR.model, item.also {
                        it.name += "jadlfhldajfh adjfh"
                    })
                })
        )

        SectionUniversalRecyclerAdapterBuilder<BasicModel, Unit, Unit>(contentOption)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.adapter = mAdapter.concatedAdapter

        mViewModel.data.observe(this, Observer {
            mAdapter.updateContent(Resource.success(it))
        })

        mViewModel.fetchData()
    }
}
