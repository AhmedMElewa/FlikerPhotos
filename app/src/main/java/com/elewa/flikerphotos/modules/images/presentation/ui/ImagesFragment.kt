package com.elewa.flikerphotos.modules.images.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.elewa.flikerphotos.R
import com.elewa.flikerphotos.base.BaseFragment
import com.elewa.flikerphotos.databinding.FragmentImagesBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ImagesFragment : BaseFragment<FragmentImagesBinding>() {

    override val bindLayout: (LayoutInflater, ViewGroup?, Boolean) -> FragmentImagesBinding
        get() = FragmentImagesBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    private fun initView(){

    }
}