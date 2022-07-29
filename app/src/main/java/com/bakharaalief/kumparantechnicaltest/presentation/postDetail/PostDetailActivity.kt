package com.bakharaalief.kumparantechnicaltest.presentation.postDetail

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bakharaalief.kumparantechnicaltest.R
import com.bakharaalief.kumparantechnicaltest.data.Result
import com.bakharaalief.kumparantechnicaltest.databinding.ActivityPostDetailBinding
import com.bakharaalief.kumparantechnicaltest.domain.model.Post
import com.bakharaalief.kumparantechnicaltest.domain.model.User
import com.bakharaalief.kumparantechnicaltest.presentation.ViewModelFactory
import com.bakharaalief.kumparantechnicaltest.presentation.user.UserActivity
import com.bumptech.glide.Glide

class PostDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPostDetailBinding
    private lateinit var post: Post
    private lateinit var user: User
    private lateinit var postDetailViewModel: PostDetailViewModel
    private lateinit var commentListAdapter: CommentListAdapter

    private val userPhoto = "https://static-cse.canva.com/blob/903207/1600w-YTfEMXMuMCs.jpg"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPostDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        post = intent.getParcelableExtra<Post>(POST_EXTRA) as Post
        user = intent.getParcelableExtra<User>(USER_EXTRA) as User

        setUpActionBar()
        setPostData()
        setUserData()
        setUpViewModel()
        setUpRv()
        getAllComment()

        //click user profile
        binding.postDetailUserPhoto.setOnClickListener {
            toUserActivity()
        }
    }

    private fun setUpActionBar() {
        setSupportActionBar(binding.topAppBar)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> true
        }
    }

    private fun setPostData() {
        binding.postDetailTitle.text = post.title
        binding.postDetailDesc.text = post.body
    }

    private fun setUserData() {
        binding.postDetailUserName.text = user.name
        binding.postDetailUserCompany.text = user.company
        Glide
            .with(this)
            .load(userPhoto)
            .centerCrop()
            .placeholder(R.drawable.ic_baseline_insert_photo_24)
            .into(binding.postDetailUserPhoto)
    }

    private fun toUserActivity() {
        val intent = Intent(this, UserActivity::class.java)
        intent.putExtra(USER_EXTRA, user)
        startActivity(intent)
    }

    private fun setUpViewModel() {
        val factory = ViewModelFactory.getInstance()
        postDetailViewModel = ViewModelProvider(this, factory)[PostDetailViewModel::class.java]
    }

    private fun setUpRv() {
        commentListAdapter = CommentListAdapter()
        binding.commentRv.adapter = commentListAdapter
        binding.commentRv.layoutManager = LinearLayoutManager(this)
        binding.commentRv.isNestedScrollingEnabled = false
    }

    private fun getAllComment() {
        postDetailViewModel.listComment(post.id).observe(this) { response ->
            when (response) {
                is Result.Loading -> {
                    showLoading(true)
                }
                is Result.Success -> {
                    commentListAdapter.submitList(response.data)
                    showLoading(false)
                }
                is Result.Error -> {
                    showLoading(false)
                    binding.errorText.text = "Tidak Bisa Mengambil Data :("
                    binding.errorText.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun showLoading(status: Boolean) {
        if (status) {
            binding.loadingIndicator.visibility = View.VISIBLE
            binding.commentRv.visibility = View.GONE
        } else {
            binding.loadingIndicator.visibility = View.GONE
            binding.commentRv.visibility = View.VISIBLE
        }
    }

    companion object {
        const val POST_EXTRA = "POST_EXTRA"
        const val USER_EXTRA = "USER_EXTRA"
    }
}