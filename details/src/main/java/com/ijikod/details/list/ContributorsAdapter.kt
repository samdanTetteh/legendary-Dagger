package com.ijikod.details.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.ijikod.details.databinding.ContributorItemBinding

class ContributorsAdapter :  RecyclerView.Adapter<ContributorsAdapter.ContributorViewHolder>(){

    private val data: MutableList<ContributorItem> = mutableListOf()

    init {
        setHasStableIds(true)
    }

    fun setContributors(contributors : List<ContributorItem>){
        data.clear()
        data.addAll(contributors)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContributorViewHolder {
        val binding = ContributorItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ContributorViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContributorViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size


    class ContributorViewHolder(private val binding: ContributorItemBinding):
    RecyclerView.ViewHolder(binding.root){

        fun bind(contributorItem: ContributorItem) {
            binding.contributorName.text = contributorItem.name
            binding.contributorAvatar.load(contributorItem.avatarUrl)
        }
    }
}