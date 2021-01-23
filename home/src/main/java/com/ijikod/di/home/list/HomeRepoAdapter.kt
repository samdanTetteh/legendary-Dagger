package com.ijikod.di.home.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ijikod.di.home.databinding.RepoItemBinding

class HomeRepoAdapter (
    private val onRepoSelected: (repoOwner: String, repoName: String) -> Unit
): RecyclerView.Adapter<HomeRepoAdapter.ReposItemViewHolder>() {


    inner class ReposItemViewHolder(private val binding: RepoItemBinding,
                                    onRepoSelected: (repoOwner: String, repoName: String) -> Unit) :
        RecyclerView.ViewHolder(binding.root) {

        private var repoItem: RepoItem? = null

        init {
            itemView.setOnClickListener {
                repoItem?.let { repo ->
                    onRepoSelected(repo.ownerName, repo.name)
                }
            }
        }


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
        return ReposItemViewHolder(binding, onRepoSelected)
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