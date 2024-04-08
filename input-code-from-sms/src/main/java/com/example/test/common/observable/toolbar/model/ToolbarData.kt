package com.example.test.common.observable.toolbar.model

import com.example.test.common.utils.emptyString

data class ToolbarData(
    val isVisible: Boolean = false,
    val hasBackNavigation: Boolean = false,
    val title: String = emptyString
)