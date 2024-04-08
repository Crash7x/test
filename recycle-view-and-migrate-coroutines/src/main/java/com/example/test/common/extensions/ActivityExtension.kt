package com.example.test.common.extensions

import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment

fun AppCompatActivity.findNavControllerById(fragmentContainerId: Int): NavController {
    val contentFragment = supportFragmentManager.findFragmentById(fragmentContainerId)
    val navController =
        if (contentFragment is NavHostFragment) contentFragment.navController else null
    return navController ?: throw IllegalStateException("NavController is not initialized")
}