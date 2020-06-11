package com.star_zero.pagingretrofitsample.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.star_zero.pagingretrofitsample.databinding.ItemRepoLoadStateBinding

class RepoLoadStateAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<RepoLoadStateAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): ViewHolder {
        val binding = ItemRepoLoadStateBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, loadState: LoadState) {
        holder.bind(loadState, retry)
    }

    class ViewHolder(
        private val binding: ItemRepoLoadStateBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(loadState: LoadState, retry: () -> Unit) {
            binding.isLoading = loadState is LoadState.Loading
            binding.isError = loadState is LoadState.Error

            binding.buttonRetry.setOnClickListener {
                retry()
            }
            binding.executePendingBindings()
        }
    }
}
