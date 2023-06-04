package com.solutionsjs.masterremotetest.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.solutionsjs.masterremotetest.data.Member
import com.solutionsjs.masterremotetest.databinding.MemberItemViewBinding

class MemberAdapter : ListAdapter<Member, MemberAdapter.MyViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Member>() {
            override fun areItemsTheSame(oldItem: Member, newItem: Member): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Member, newItem: Member): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            MemberItemViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val member = getItem(position)
        holder.bind(member)
    }

    inner class MyViewHolder(private val binding: MemberItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(member: Member) {
            binding.nomeText2.text = member.name
            binding.idText2.text = member.id
            binding.cityText2.text = member.city

            binding.nomeText2.contentDescription = member.name
            binding.idText2.contentDescription = member.id
            binding.cityText2.contentDescription = member.city
        }
    }
}
