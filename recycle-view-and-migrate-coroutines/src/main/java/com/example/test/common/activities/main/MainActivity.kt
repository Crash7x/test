package com.example.test.common.activities.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.NavController
import com.example.recycle.view.and.migrate.coroutines.R
import com.example.recycle.view.and.migrate.coroutines.databinding.ActivityMainBinding
import com.example.test.common.activities.main.di.DaggerMainActivityComponent
import com.example.test.common.application.appComponent
import com.example.test.common.extensions.findNavControllerById
import com.example.test.common.extensions.observe
import com.example.test.common.view.model.factory.assistedViewModel
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: MainViewModel.Factory
    private val viewModel by assistedViewModel { factory.create() }

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private val navController: NavController by lazy(LazyThreadSafetyMode.NONE) {
        findNavControllerById(R.id.nav_host_fragment)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerMainActivityComponent.factory().create(appComponent).inject(this)
        setContentView(binding.root)
        setupToolbar()
        setupLoader()
    }

    private fun setupToolbar() {
        binding.toolbar.navigateBack.setOnClickListener {
            navController.navigateUp()
        }

        viewModel.toolbarData.observe(this) {
            binding.toolbar.root.isVisible = it.isVisible
            if (!it.isVisible) return@observe
            binding.toolbar.navigateBack.isVisible = it.hasBackNavigation
            binding.toolbar.title.text = it.title
        }
    }

    private fun setupLoader() {
        viewModel.loader.observe(this) {
            binding.loader.isVisible = it
            binding.navHostFragment.isVisible = !it
        }
    }
}