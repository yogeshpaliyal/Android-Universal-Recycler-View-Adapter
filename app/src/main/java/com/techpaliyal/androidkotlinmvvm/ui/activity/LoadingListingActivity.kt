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
import com.techpaliyal.androidkotlinmvvm.model.UserModel
import com.techpaliyal.androidkotlinmvvm.ui.view_model.LoadingListingViewModel
import com.techpaliyal.androidkotlinmvvm.ui.view_model.initViewModel
import com.yogeshpaliyal.universal_adapter.adapter.SectionUniversalRecyclerAdapterBuilder
import com.yogeshpaliyal.universal_adapter.adapter.UniversalAdapterViewType
import com.yogeshpaliyal.universal_adapter.adapter.UniversalRecyclerAdapter
import com.yogeshpaliyal.universal_adapter.utils.Resource

/**
 * @author Yogesh Paliyal
 * yogeshpaliyal.foss@gmail.com
 * https://techpaliyal.com
 * https://yogeshpaliyal.com
 * Created Date : 9 January 2020
 */
class LoadingListingActivity : AppCompatActivity() {
    lateinit var binding: ActivityListingBinding


    companion object {
        @JvmStatic
        fun start(context: Context) {
            val starter = Intent(context, LoadingListingActivity::class.java)
            context.startActivity(starter)
        }
    }

    private val mViewModel by lazy {
        initViewModel(LoadingListingViewModel::class.java)
    }

    private val mAdapter by lazy {

        val header = UniversalRecyclerAdapter.Builder(
            lifecycleOwner = this,
            content = UniversalAdapterViewType.Content(
                R.layout.item_simple,
                listener = object : BasicListener<BasicModel> {
                    override fun onClick(model: BasicModel) {
                        Toast.makeText(this@LoadingListingActivity, model.name, Toast.LENGTH_SHORT)
                            .show()
                    }
                }),
            data = Resource.success(ArrayList<BasicModel>().also {
                it.add(BasicModel(name = "Helllo Header 123 testing"))
            })
        )

        val content = UniversalRecyclerAdapter.Builder<UserModel>(R.layout.item_user,
            resourceLoading = R.layout.layout_loading_full_page,
            defaultLoadingItems = 1,
            mListener = object : BasicListener<UserModel> {
                override fun onClick(model: UserModel) {
                    Toast.makeText(this@LoadingListingActivity, model.name, Toast.LENGTH_SHORT)
                        .show()
                }
            })


        SectionUniversalRecyclerAdapterBuilder<UserModel, BasicModel, Unit>(content, header, null)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.adapter = mAdapter.concatedAdapter

        mViewModel.data.observe(this, Observer {
            mAdapter.updateContent(it)
        })

        mViewModel.fetchData()
    }
}