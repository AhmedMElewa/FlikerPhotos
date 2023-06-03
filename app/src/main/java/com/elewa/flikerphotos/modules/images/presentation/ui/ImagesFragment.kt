package com.elewa.flikerphotos.modules.images.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.elewa.flikerphotos.base.BaseFragment
import com.elewa.flikerphotos.core.extensions.parseError
import com.elewa.flikerphotos.databinding.FragmentDetailsBinding
import com.elewa.flikerphotos.databinding.FragmentImagesBinding
import com.elewa.flikerphotos.modules.images.presentation.adapter.ImagesLoadStateAdapter
import com.elewa.flikerphotos.modules.images.presentation.adapter.ImagesPagingAdapter
import com.elewa.flikerphotos.modules.images.presentation.viewmodel.ImagesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class ImagesFragment : BaseFragment<FragmentImagesBinding>() {


    override val bindLayout: (LayoutInflater, ViewGroup?, Boolean) -> FragmentImagesBinding
        get() = FragmentImagesBinding::inflate

    @Inject
    lateinit var imagesPagingAdapter: ImagesPagingAdapter

    @Inject
    lateinit var imagesLoadStateAdapter: ImagesLoadStateAdapter
    private val imagesViewModel: ImagesViewModel by viewModels()
    private val itemDecorator by lazy { SimpleDividerItemDecoration(requireContext()) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initObservation()
        initListeners()

    }


    private fun initView() {
        binding.pagingAdapter = imagesPagingAdapter
        binding.loadAdapter = imagesLoadStateAdapter
        binding.itemDecorator = itemDecorator
    }

    private fun initListeners() {
        imagesPagingAdapter.onImageClickListener = {
            val action =
                ImagesFragmentDirections.actionImagesFragmentToDetailsFragment(it.previewUrl)
            findNavController().navigate(action)
        }
        imagesPagingAdapter.addLoadStateListener {
            binding.layoutLoading.clLoading.isVisible = it.refresh is LoadState.Loading
            if (it.refresh is LoadState.Error && imagesPagingAdapter.snapshot().isEmpty())
                binding.layoutError.tvError.text =
                    (it.refresh as LoadState.Error).error.parseError().message
            binding.layoutError.clLoading.isVisible =
                it.refresh is LoadState.Error && imagesPagingAdapter.snapshot().isEmpty()
        }
        binding.layoutError.btnRetry.setOnClickListener {
            imagesPagingAdapter.retry()
        }
        imagesLoadStateAdapter.onRetyButonClickListener = {
            imagesPagingAdapter.retry()
        }
    }

    private fun initObservation() {
        lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                imagesViewModel.imagesState.collect { imagesState ->
                    imagesState.data?.let { imagesPagingData ->
                        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
                            imagesPagingAdapter.submitData(imagesPagingData)
                        }
                    }
                }
            }
        }
    }
}