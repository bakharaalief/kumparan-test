package com.bakharaalief.kumparantechnicaltest.presentation.main

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bakharaalief.kumparantechnicaltest.databinding.ItemPostBinding
import com.bakharaalief.kumparantechnicaltest.domain.model.Post
import com.bakharaalief.kumparantechnicaltest.domain.model.User
import com.bakharaalief.kumparantechnicaltest.presentation.postDetail.PostDetailActivity

class PostListAdapter(val getUserData: (post: Post, holder: MyViewHolder) -> Unit) :
    ListAdapter<Post, PostListAdapter.MyViewHolder>(DIFF_CALLBACK) {

    class MyViewHolder(private val itemPostBinding: ItemPostBinding) :
        RecyclerView.ViewHolder(itemPostBinding.root) {

        fun bind(post: Post, user: User?) {
            itemPostBinding.itemPostTitle.text = post.title
            itemPostBinding.itemPostDesc.text = post.body
            itemPostBinding.itemPostPersonName.text = user?.name ?: "tidak ditemukan"
            itemPostBinding.itemPostCompanyName.text = user?.company ?: "tidak ditemukan"

            itemPostBinding.itemPostCard.setOnClickListener {
                if (user != null) {
                    val intent = Intent(it.context, PostDetailActivity::class.java)
                    intent.putExtra(PostDetailActivity.POST_EXTRA, post)
                    intent.putExtra(PostDetailActivity.USER_EXTRA, user)
                    it.context.startActivity(intent)
                } else {
                    Toast.makeText(it.context, "Maaf Tidak Bisa dibuka :(", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }

        fun loading(status: Boolean) {
            if (status) {
                itemPostBinding.loadingIndicator.visibility = View.VISIBLE
                itemPostBinding.itemPostPersonIcon.visibility = View.GONE
                itemPostBinding.itemPostCompanyIcon.visibility = View.GONE
                itemPostBinding.itemPostPersonName.visibility = View.GONE
                itemPostBinding.itemPostCompanyName.visibility = View.GONE
            } else {
                itemPostBinding.loadingIndicator.visibility = View.GONE
                itemPostBinding.itemPostPersonIcon.visibility = View.VISIBLE
                itemPostBinding.itemPostCompanyIcon.visibility = View.VISIBLE
                itemPostBinding.itemPostPersonName.visibility = View.VISIBLE
                itemPostBinding.itemPostCompanyName.visibility = View.VISIBLE
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = ItemPostBinding.inflate(inflater, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = getItem(position)
        getUserData(data, holder)
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