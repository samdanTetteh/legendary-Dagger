package com.ijikod.di.home.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import com.ijikod.di.home.HomeViewModel
import com.ijikod.di.home.R
import com.ijikod.di.home.databinding.RepoItemBinding
import com.ijikod.poweradapter.ItemRenderer
import javax.inject.Inject

class RepoItemRenderer @Inject constructor(
    private val homeViewModel: HomeViewModel
) : ItemRenderer<RepoItem>{

    override fun layoutRes(): Int = R.layout.repo_item

    override fun createView(parent: ViewGroup): View {
        val binding =  RepoItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)

        val viewBinder = RepoItemViewBinder(binding, homeViewModel)
        binding.root.tag = viewBinder
        return binding.root
    }

    override fun render(itemView: View, item: RepoItem) {
        (itemView.tag as RepoItemViewBinder).bind(item)
    }
}


class RepoItemViewBinder(
    private val binding: RepoItemBinding,
    private val homeViewModel: HomeViewModel
){

    private var repoItem: RepoItem? = null

    init {
        binding.root.setOnClickListener {
            repoItem?.let { repo ->
                homeViewModel.onRepoSelected(repo.ownerName, repo.name)
            }
        }
    }


    fun bind(repoItem: RepoItem){
        binding.repoName.text = repoItem.name
        binding.appDecription.text = repoItem.description
        binding.forkCount.text = repoItem.forkCount.toString()
        binding.starCount.text = repoItem.startCount.toString()
    }

}