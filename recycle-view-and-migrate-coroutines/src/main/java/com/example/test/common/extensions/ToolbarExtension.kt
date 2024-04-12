package com.example.test.common.extensions

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.FragmentNavigator
import com.example.recycle.view.and.migrate.coroutines.R
import com.example.test.common.domain.model.toolbar.ToolbarData
import com.example.test.screens.characters.CharactersFragment
import com.example.test.screens.detail.character.CharactersDetailFragment

private val toolbarFragments = mapOf(
    CharactersFragment::class.java.name to ToolbarData(R.string.characters_fragment_toolbar_title, false),
    CharactersDetailFragment::class.java.name to ToolbarData(R.string.character_detail_fragment_toolbar_title, true)

)

class OnChangeToolbar(val change: (ToolbarData?) -> Unit): NavController.OnDestinationChangedListener {
    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?
    ) {
        val fragmentClassName = (controller.currentDestination as FragmentNavigator.Destination).className
        change(toolbarFragments[fragmentClassName])
    }

}