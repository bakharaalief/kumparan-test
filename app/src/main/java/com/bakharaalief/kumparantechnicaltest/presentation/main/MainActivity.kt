package com.bakharaalief.kumparantechnicaltest.presentation.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bakharaalief.kumparantechnicaltest.data.remote.Result
import com.bakharaalief.kumparantechnicaltest.databinding.ActivityMainBinding
import com.bakharaalief.kumparantechnicaltest.presentation.ViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel
    private lateinit var postListAdapter: PostListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setActionBar()
        setUpViewModel()
        setUpRv()
        getAllPost()
    }

    private fun setActionBar() {
        setSupportActionBar(binding.topAppBar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    private fun setUpViewModel() {
        val factory = ViewModelFactory.getInstance()
        mainViewModel = ViewModelProvider(viewModelStore, factory)[MainViewModel::class.java]
    }

    private fun setUpRv() {
        postListAdapter = PostListAdapter { post, holder ->
            mainViewModel.getUserDetail(post.userId).observe(this) { response ->
                when (response) {
                    is Result.Loading -> {
                        holder.bind(post, null)
                        holder.loading(true)
                    }
                    is Result.Success -> {
                        holder.bind(post, response.data)
                        holder.loading(false)
                    }
                    is Result.Error -> {
                        holder.bind(post, null)
                        holder.loading(false)
                    }
                }
            }
        }
        binding.postRv.adapter = postListAdapter
        binding.postRv.layoutManager = LinearLayoutManager(this)
    }

    private fun getAllPost() {
        mainViewModel.getAllPost().observe(this) { response ->
            when (response) {
                is Result.Loading -> showLoading(true)
                is Result.Success -> {
                    postListAdapter.submitList(response.data)
                    showLoading(false)
                }
                is Result.Error -> {
                    showLoading(false)
                    binding.errorText.text = response.error
                    binding.errorText.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun showLoading(status: Boolean) {
        binding.loadingIndicator.visibility = if (status) View.VISIBLE else View.GONE
    }
}