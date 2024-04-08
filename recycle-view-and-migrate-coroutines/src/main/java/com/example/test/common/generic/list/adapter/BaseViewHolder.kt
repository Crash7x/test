package com.example.test.common.generic.list.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Base class for ViewHolder logic. Created special for create new viewHolder
 * with binding or other technologies
 */
abstract class BaseViewHolder<T : Item>(root: View) : RecyclerView.ViewHolder(root) {
    /**
     * Binds the given item's data to this view holder.
     */
    abstract fun bind(item: T)

    /**
     * Partially binds the given item's data to this view holder.
     *
     * For detailed information refer to [RecyclerView.Adapter.onBindViewHolder].
     */
    open fun bind(item: T, payloads: List<Any>) = Unit

    /**
     * Releases resources when its recycled.
     */
    open fun unbind() = Unit
}