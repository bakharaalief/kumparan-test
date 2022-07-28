package com.bakharaalief.kumparantechnicaltest.presentation.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bakharaalief.kumparantechnicaltest.databinding.ItemPostBinding
import com.bakharaalief.kumparantechnicaltest.domain.model.Post

class PostListAdapter : ListAdapter<Post, PostListAdapter.MyViewHolder>(DIFF_CALLBACK) {

    class MyViewHolder(private val itemPostBinding: ItemPostBinding) :
        RecyclerView.ViewHolder(itemPostBinding.root) {

        fun bind(post: Post) {
            itemPostBinding.itemPostTitle.text = post.title
            itemPostBinding.itemPostDesc.text = post.body
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = ItemPostBinding.inflate(inflater)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = getItem(position)
        holder.bind(data)
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Post>() {
            override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
                return oldItem == newItem
            }

        }
    }


}