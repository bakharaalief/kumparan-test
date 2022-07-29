package com.bakharaalief.kumparantechnicaltest.presentation.photoDetail

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.bakharaalief.kumparantechnicaltest.R
import com.bakharaalief.kumparantechnicaltest.databinding.ActivityPhotoDetailBinding
import com.bakharaalief.kumparantechnicaltest.domain.model.Photo
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders

class PhotoDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPhotoDetailBinding
    private lateinit var photo: Photo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPhotoDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        photo = intent.getParcelableExtra<Photo>(PHOTO_EXTRA) as Photo

        setUpActionBar()
        setPhotoData()
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

    private fun setPhotoData() {
        //we have to do this bcz problem in placeholder website
        val url = GlideUrl(
            photo.url, LazyHeaders.Builder()
                .addHeader("User-Agent", "your-user-agent")
                .build()
        )

        Glide
            .with(this)
            .load(url)
            .placeholder(R.drawable.ic_baseline_insert_photo_24)
            .into(binding.photoDetailPhoto)

        binding.photoDetailName.text = photo.title
    }

    companion object {
        const val PHOTO_EXTRA = "PHOTO_EXTRA"
    }
}