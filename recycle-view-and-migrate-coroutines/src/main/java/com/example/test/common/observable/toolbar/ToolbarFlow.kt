package com.example.test.common.observable.toolbar

import com.example.test.common.observable.toolbar.model.ToolbarData
import kotlinx.coroutines.flow.MutableSharedFlow

typealias ToolbarFlow = MutableSharedFlow<ToolbarData>