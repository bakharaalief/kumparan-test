package com.bakharaalief.kumparantechnicaltest.presentation.user

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.bakharaalief.kumparantechnicaltest.R
import com.bakharaalief.kumparantechnicaltest.data.Result
import com.bakharaalief.kumparantechnicaltest.databinding.ActivityUserBinding
import com.bakharaalief.kumparantechnicaltest.domain.model.User
import com.bakharaalief.kumparantechnicaltest.presentation.ViewModelFactory
import com.bakharaalief.kumparantechnicaltest.presentation.postDetail.PostDetailActivity
import com.bumptech.glide.Glide

class UserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserBinding
    private lateinit var user: User
    private lateinit var userViewModel: UserViewModel
    private lateinit var albumListAdapter: AlbumListAdapter

    private val userPhoto = "https://static-cse.canva.com/blob/903207/1600w-YTfEMXMuMCs.jpg"
    private val gridCount = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        user = intent.getParcelableExtra<User>(PostDetailActivity.USER_EXTRA) as User

        setUpActionBar()
        setUserData()
        setUpViewModel()
        setUpRv()
        getUserAlbum()
    }

    private fun setUpActionBar() {
        setSupportActionBar(binding.topAppBar)
        supportActionBar?.title = user.name
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

    private fun setUserData() {
        binding.userName.text = user.name
        binding.userCompany.text = user.company
        binding.userEmail.text = user.email
        binding.userAddress.text =
            user.address.suite + ", " + user.address.street + ", " + user.address.city + " " + user.address.zipcode
        Glide
            .with(this)
            .load(userPhoto)
            .centerCrop()
            .placeholder(R.drawable.ic_baseline_insert_photo_24)
            .into(binding.userPhoto)
    }

    private fun setUpViewModel() {
        val factory = ViewModelFactory.getInstance()
        userViewModel = ViewModelProvider(this, factory)[UserViewModel::class.java]
    }

    private fun setUpRv() {
        albumListAdapter = AlbumListAdapter()
        binding.albumRv.adapter = albumListAdapter
        binding.albumRv.layoutManager = GridLayoutManager(this, 2)
        binding.albumRv.isNestedScrollingEnabled = false
    }

    private fun getUserAlbum() {
        userViewModel.getUserAlbum(user.id).observe(this) { response ->
            when (response) {
                is Result.Loading -> {
                    showLoading(true)
                }
                is Result.Success -> {
                    albumListAdapter.submitList(response.data)
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
            binding.albumRv.visibility = View.GONE
        } else {
            binding.loadingIndicator.visibility = View.GONE
            binding.albumRv.visibility = View.VISIBLE
        }
    }
}