package com.example.test.common.generic.list.adapter

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

/**
 * Base implementation of [RecyclerView.ViewHolder] for providing support for ViewBinding.
 */
abstract class BindingViewHolder<T : Item>(binding: ViewBinding) : BaseViewHolder<T>(binding.root) {

    /**
     * Binds the given item's data to this view holder.
     */
    abstract override fun bind(item: T)

    /**
     * Partially binds the given item's data to this view holder.
     *
     * For detailed information refer to [RecyclerView.Adapter.onBindViewHolder].
     */
    override fun bind(item: T, payloads: List<Any>) = Unit

    /**
     * Releases resources when its recycled.
     */
    override fun unbind() = Unit
}