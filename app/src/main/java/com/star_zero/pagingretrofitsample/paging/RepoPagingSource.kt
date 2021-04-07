package com.star_zero.pagingretrofitsample.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.star_zero.pagingretrofitsample.api.GitHubAPI
import com.star_zero.pagingretrofitsample.data.Repo
import timber.log.Timber

class RepoPagingSource(
    private val api: GitHubAPI
) : PagingSource<Int, Repo>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Repo> {
        return try {
            val page = params.key ?: 1
            val response = api.repos("google", page, params.loadSize)

            val repos = response.body()
            if (repos != null) {
                var next: Int? = null
                // Check if there is next page
                response.headers()["Link"]?.let { link ->
                    val regex = Regex("rel=\"next\"")
                    if (regex.containsMatchIn(link)) {
                        next = page + 1
                    }
                }
                LoadResult.Page(
                    data = repos,
                    prevKey = null,
                    nextKey = next
                )
            } else {
                LoadResult.Page(
                    data = emptyList(),
                    prevKey = null,
                    nextKey = null
                )
            }
        } catch (e: Exception) {
            Timber.w(e)
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Repo>): Int? {
        return null
    }
}
