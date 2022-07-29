package com.bakharaalief.kumparantechnicaltest.presentation.user

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bakharaalief.kumparantechnicaltest.R
import com.bakharaalief.kumparantechnicaltest.databinding.ItemAlbumBinding
import com.bakharaalief.kumparantechnicaltest.domain.model.Album
import com.bakharaalief.kumparantechnicaltest.presentation.albumDetail.AlbumDetailActivity
import com.bumptech.glide.Glide

class AlbumListAdapter : ListAdapter<Album, AlbumListAdapter.MyViewHolder>(DIFF_CALLBACK) {


    class MyViewHolder(private val itemAlbumBinding: ItemAlbumBinding) :
        RecyclerView.ViewHolder(itemAlbumBinding.root) {

        private val albumPhoto =
            "https://cdn.mos.cms.futurecdn.net/2dgsUd3Yi8yUeccvoAdAYD.jpg"

        fun bind(album: Album) {
            itemAlbumBinding.itemAlbumName.text = album.title

            Glide
                .with(itemView.context)
                .load(albumPhoto)
                .centerCrop()
                .placeholder(R.drawable.ic_baseline_insert_photo_24)
                .into(itemAlbumBinding.itemAlbumCover)

            itemAlbumBinding.itemAlbumCard.setOnClickListener {
                val intent = Intent(it.context, AlbumDetailActivity::class.java)
                intent.putExtra(AlbumDetailActivity.ALBUM_EXTRA, album)
                it.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = ItemAlbumBinding.inflate(inflater, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = getItem(position)
        holder.bind(data)
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Album>() {
            override fun areItemsTheSame(oldItem: Album, newItem: Album): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Album, newItem: Album): Boolean {
                return oldItem == newItem
            }
        }
    }
}