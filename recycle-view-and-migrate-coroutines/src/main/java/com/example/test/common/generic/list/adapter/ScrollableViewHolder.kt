package com.example.test.common.generic.list.adapter

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class ScrollableViewHolder<T : Item>(binding: ViewBinding) :
    BindingViewHolder<T>(binding) {
    protected abstract val layoutManager: RecyclerView.LayoutManager?
    private var restoreScrollState: Boolean = true

    protected fun restoreScrollState(key: Int, listener: (key: Int, position: Int) -> Int) {
        if (restoreScrollState) {
            layoutManager.restoreScrollState(key, this, listener)
            restoreScrollState = false
        }
    }

    protected fun saveScrollState(
        key: Int,
        listener: (key: Int, osition: Int, firstVisiblePosition: Int) -> Unit
    ) {
        layoutManager.saveScrollState(key, this, listener)
    }
}