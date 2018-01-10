package com.star_zero.pagingretrofitsample.ui

import android.arch.paging.PagedListAdapter
import android.databinding.DataBindingUtil
import android.support.v7.recyclerview.extensions.DiffCallback
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.star_zero.pagingretrofitsample.R
import com.star_zero.pagingretrofitsample.data.Repo
import com.star_zero.pagingretrofitsample.databinding.ItemRepoBinding

class RepoAdapter : PagedListAdapter<Repo, RepoAdapter.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.item_repo, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.bindTo(getItem(position))
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

        private val binding: ItemRepoBinding = DataBindingUtil.bind(itemView)

        fun bindTo(repo: Repo?) {
            binding.repo = repo
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffCallback<Repo>() {
            override fun areItemsTheSame(oldItem: Repo, newItem: Repo): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Repo, newItem: Repo): Boolean {
                return oldItem == newItem
            }
        }
    }
}

