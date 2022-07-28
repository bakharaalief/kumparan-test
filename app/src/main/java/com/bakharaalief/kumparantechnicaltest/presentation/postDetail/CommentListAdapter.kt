package com.bakharaalief.kumparantechnicaltest.presentation.postDetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bakharaalief.kumparantechnicaltest.databinding.ItemCommentBinding
import com.bakharaalief.kumparantechnicaltest.domain.model.Comment

class CommentListAdapter() : ListAdapter<Comment, CommentListAdapter.MyViewHolder>(DIFF_CALLBACK) {

    class MyViewHolder(private val itemCommentBinding: ItemCommentBinding) :
        RecyclerView.ViewHolder(itemCommentBinding.root) {

        fun bind(comment: Comment) {
            itemCommentBinding.itemCommentName.text = comment.name
            itemCommentBinding.itemCommentDesc.text = comment.body
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = ItemCommentBinding.inflate(inflater, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = getItem(position)
        holder.bind(data)
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Comment>() {
            override fun areItemsTheSame(oldItem: Comment, newItem: Comment): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Comment, newItem: Comment): Boolean {
                return oldItem == newItem
            }
        }
    }
}