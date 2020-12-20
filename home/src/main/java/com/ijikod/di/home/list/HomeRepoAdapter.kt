package com.ijikod.di.home.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ijikod.di.home.databinding.RepoItemBinding

class HomeRepoAdapter : RecyclerView.Adapter<HomeRepoAdapter.ReposItemViewHolder>() {


    inner class ReposItemViewHolder(private val binding: RepoItemBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind(repoItem: RepoItem) {
            binding.repoName.text = repoItem.name
            binding.appDecription.text = repoItem.description
            binding.forkCount.text = repoItem.forkCount.toString()
            binding.starCount.text = repoItem.startCount.toString()
        }
    }


    private val data: MutableList<RepoItem> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReposItemViewHolder {
        val binding = RepoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ReposItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReposItemViewHolder, position: Int) {
        with(holder) {
            bind(data[position])
        }
    }

    override fun getItemCount(): Int = data.size


    fun setRepoItems(repoItems: List<RepoItem>) {
        data.clear()
        data.addAll(repoItems)
        notifyDataSetChanged()
    }
}