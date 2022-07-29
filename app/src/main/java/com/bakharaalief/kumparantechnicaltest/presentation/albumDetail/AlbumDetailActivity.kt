package com.bakharaalief.kumparantechnicaltest.presentation.albumDetail

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.bakharaalief.kumparantechnicaltest.data.Result
import com.bakharaalief.kumparantechnicaltest.databinding.ActivityAlbumDetailBinding
import com.bakharaalief.kumparantechnicaltest.domain.model.Album
import com.bakharaalief.kumparantechnicaltest.presentation.ViewModelFactory

class AlbumDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAlbumDetailBinding
    private lateinit var album: Album
    private lateinit var albumDetailViewModel: AlbumDetailViewModel
    private lateinit var photoListAdapter: PhotoListAdapter

    private val gridCount = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAlbumDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        album = intent.getParcelableExtra<Album>(ALBUM_EXTRA) as Album

        setUpActionBar()
        setAlbumData()
        setUpViewModel()
        setUpRv()
        getAlbumPhoto()
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

    private fun setAlbumData() {
        binding.albumDetailName.text = album.title
    }

    private fun setUpViewModel() {
        val factory = ViewModelFactory.getInstance()
        albumDetailViewModel = ViewModelProvider(this, factory)[AlbumDetailViewModel::class.java]
    }

    private fun setUpRv() {
        photoListAdapter = PhotoListAdapter()
        binding.photoRv.adapter = photoListAdapter
        binding.photoRv.layoutManager = GridLayoutManager(this, gridCount)
        binding.photoRv.isNestedScrollingEnabled = false
    }

    private fun getAlbumPhoto() {
        albumDetailViewModel.getAlbumPhoto(album.id).observe(this) { response ->
            when (response) {
                is Result.Loading -> {
                    showLoading(true)
                }
                is Result.Success -> {
                    photoListAdapter.submitList(response.data)
                    binding.nestedScrollView.visibility = View.VISIBLE
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

    companion object {
        const val ALBUM_EXTRA = "ALBUM_EXTRA"
    }
}