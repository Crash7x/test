package com.example.test.common.domain.model.toolbar

import androidx.annotation.StringRes

data class ToolbarData(
    @StringRes
    val title: Int,
    val isBack: Boolean
)