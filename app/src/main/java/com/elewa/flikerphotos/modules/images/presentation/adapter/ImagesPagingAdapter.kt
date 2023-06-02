package com.elewa.flikerphotos.modules.images.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.elewa.flikerphotos.databinding.ImageListItemBinding
import com.elewa.flikerphotos.modules.images.domain.entity.ImageEntity
import javax.inject.Inject


class ImagesPagingAdapter @Inject constructor() :
    PagingDataAdapter<ImageEntity, ImagesPagingAdapter.ImagesViewHolder>(DiffCallback) {

    var onImageClickListener: ((ImageEntity) -> Unit)? = null

    private object DiffCallback : DiffUtil.ItemCallback<ImageEntity>() {
        override fun areItemsTheSame(oldItem: ImageEntity, newItem: ImageEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ImageEntity, newItem: ImageEntity): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagesViewHolder {
        val binding =
            ImageListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImagesViewHolder(binding, viewType)
    }

    override fun onBindViewHolder(holder: ImagesViewHolder, position: Int) {
        val item = getItem(position)
        item?.let {
            holder.bind(item)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position % 6 == 0 && position != 0) {
            Banner
        } else Image
    }

    inner class ImagesViewHolder(
        private val binding: ImageListItemBinding,
        private val type: Int
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(imageEntity: ImageEntity) {
            if (type == Banner) {
                binding.entity = ImageEntity(
                    "0",
                    "https://storage.googleapis.com/website-production/uploads/2023/01/audible-banner-ad-example.png"
                )
            } else {
                binding.entity = imageEntity
                binding.root.setOnClickListener {
                    onImageClickListener?.invoke(imageEntity)
                }
            }
        }
    }

    companion object {
        val Banner = 0
        val Image = 1
    }
}