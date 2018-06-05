package com.star_zero.pagingretrofitsample.paging

import android.arch.paging.DataSource
import com.star_zero.pagingretrofitsample.api.GitHubAPI
import com.star_zero.pagingretrofitsample.data.Repo

class RepoDataSourceFactory(api: GitHubAPI) : DataSource.Factory<Int, Repo>() {

    val source = PageKeyedRepoDataSource(api)

    override fun create(): DataSource<Int, Repo> {
        return source
    }
}