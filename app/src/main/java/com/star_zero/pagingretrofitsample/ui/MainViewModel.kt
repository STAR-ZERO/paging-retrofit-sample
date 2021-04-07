package com.star_zero.pagingretrofitsample.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.map
import com.star_zero.pagingretrofitsample.api.GitHubAPI
import com.star_zero.pagingretrofitsample.paging.RepoPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val api: GitHubAPI
) : ViewModel() {

    val reposFlow = Pager(
        PagingConfig(pageSize = PAGE_SIZE, initialLoadSize = PAGE_SIZE)
    ) {
        RepoPagingSource(api)
    }.flow
        .map { pagingData ->
            pagingData.map {
                UiModel(
                    it.id,
                    it.fullName
                )
            }
        }.cachedIn(viewModelScope)

    companion object {
        private const val PAGE_SIZE = 20
    }
}
