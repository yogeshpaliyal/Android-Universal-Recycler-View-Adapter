package com.techpaliyal.androidkotlinmvvm.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState

class GithubPagingSource(
        private val service: GithubService,
        private val query: String
) : PagingSource<Int, Repo>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Repo> {
        TODO("Not yet implemented")
    }
   override fun getRefreshKey(state: PagingState<Int, Repo>): Int? {
        TODO("Not yet implemented")
    }

}