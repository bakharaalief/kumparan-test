package com.bakharaalief.kumparantechnicaltest.presentation.albumDetail

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bakharaalief.kumparantechnicaltest.R
import com.bakharaalief.kumparantechnicaltest.databinding.ItemPhotoBinding
import com.bakharaalief.kumparantechnicaltest.domain.model.Photo
import com.bakharaalief.kumparantechnicaltest.presentation.photoDetail.PhotoDetailActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders

class PhotoListAdapter : ListAdapter<Photo, PhotoListAdapter.MyViewHolder>(DIFF_CALLBACK) {

    class MyViewHolder(private val itemPhotoBinding: ItemPhotoBinding) :
        RecyclerView.ViewHolder(itemPhotoBinding.root) {

        fun bind(photo: Photo) {

            //we have to do this bcz problem in placeholder website
            val url = GlideUrl(
                photo.thumbnailUrl, LazyHeaders.Builder()
                    .addHeader("User-Agent", "your-user-agent")
                    .build()
            )

            Glide
                .with(itemView.context)
                .load(url)
                .centerCrop()
                .placeholder(R.drawable.ic_baseline_insert_photo_24)
                .into(itemPhotoBinding.itemPhotoThumbnail)

            itemPhotoBinding.itemPhotoCard.setOnClickListener {
                val intent = Intent(it.context, PhotoDetailActivity::class.java)
                intent.putExtra(PhotoDetailActivity.PHOTO_EXTRA, photo)
                it.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = ItemPhotoBinding.inflate(inflater, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = getItem(position)
        holder.bind(data)
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Photo>() {
            override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
                return oldItem == newItem
            }
        }
    }
}