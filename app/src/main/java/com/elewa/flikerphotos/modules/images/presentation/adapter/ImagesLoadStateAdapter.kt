package com.elewa.flikerphotos.modules.images.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.elewa.flikerphotos.core.extensions.parseError
import com.elewa.flikerphotos.databinding.LoadingListItemBinding
import javax.inject.Inject

class ImagesLoadStateAdapter @Inject constructor() :
    LoadStateAdapter<ImagesLoadStateAdapter.LoadStateViewHolder>() {
    var onRetyButonClickListener: (() -> Unit)? = null
    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        val binding =
            LoadingListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LoadStateViewHolder(binding)
    }

    inner class LoadStateViewHolder(private val binding: LoadingListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(loadState: LoadState) {
            binding.pb.isVisible = loadState is LoadState.Loading
            binding.tvError.isVisible = loadState is LoadState.Error
            binding.btnRetry.isVisible = loadState is LoadState.Error
            if (loadState is LoadState.Error)
                binding.tvError.text = loadState.error.parseError().message
            binding.btnRetry.setOnClickListener {
                onRetyButonClickListener?.invoke()
            }
        }
    }
}