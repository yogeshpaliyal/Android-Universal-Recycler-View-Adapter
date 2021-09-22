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
import com.techpaliyal.androidkotlinmvvm.listeners.BasicListener
import com.techpaliyal.androidkotlinmvvm.listeners.UsersListener
import com.techpaliyal.androidkotlinmvvm.model.UserModel
import com.techpaliyal.androidkotlinmvvm.ui.view_model.LoadingListingViewModel
import com.techpaliyal.androidkotlinmvvm.ui.view_model.initViewModel
import com.yogeshpaliyal.universalAdapter.adapter.UniversalAdapterViewType
import com.yogeshpaliyal.universalAdapter.adapter.UniversalRecyclerAdapter

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
        UniversalRecyclerAdapter.Builder<UserModel>(
            lifecycleOwner = this,
            content = UniversalAdapterViewType.Content(resource = R.layout.item_user,
                object : UsersListener {
                    override fun onLikeClicked(binding: ViewDataBinding, model: UserModel) {

                    }
                    override fun onClick(model: UserModel) {
                        Toast.makeText(this@LoadingListingActivity, model.name, Toast.LENGTH_SHORT)
                            .show()
                    }
                }),
            loading = UniversalAdapterViewType.Loading(
                resourceLoading = R.layout.layout_loading_full_page,
                defaultLoadingItems = 1
            ),
            error = UniversalAdapterViewType.Error(errorLayout = R.layout.item_error),
            loadingFooter = UniversalAdapterViewType.LoadingFooter(loaderFooter = R.layout.item_loading_more)
        ).build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.adapter = mAdapter.getAdapter()

        mViewModel.data.observe(this, Observer {
            mAdapter.updateData(it)
        })

        mViewModel.fetchData()
    }
}