package com.elewa.flikerphotos.modules.main.presentation.ui

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.elewa.flikerphotos.R
import com.elewa.flikerphotos.base.BaseActivity
import com.elewa.flikerphotos.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun getViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initView()
    }

    private fun initView(){
        appBarConfiguration = AppBarConfiguration(findMainNavController().graph)
        setupActionBarWithNavController(findMainNavController(), appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        return findMainNavController().navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    private fun findMainNavController(): NavController {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        return navHostFragment.navController
    }
}