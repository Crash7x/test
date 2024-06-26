package com.example.test.common.activities.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.NavController
import com.example.recycle.view.and.migrate.coroutines.R
import com.example.recycle.view.and.migrate.coroutines.databinding.ActivityMainBinding
import com.example.test.common.activities.main.di.DaggerMainActivityComponent
import com.example.test.common.application.appComponent
import com.example.test.common.extensions.OnChangeToolbar
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

    private fun setupLoader() {
        viewModel.loader.observe(this) {
            binding.loader.isVisible = it
            binding.navHostFragment.isVisible = !it
        }
    }

    private fun setupToolbar() {
        navController.addOnDestinationChangedListener(OnChangeToolbar {
            binding.toolbar.root.isVisible = it != null
            if (it == null) return@OnChangeToolbar
            binding.toolbar.title.text = getString(it.title)
            binding.toolbar.navigateBack.isVisible = it.isBack
        })

        binding.toolbar.root.setOnClickListener {
            navController.navigateUp()
        }
    }
}