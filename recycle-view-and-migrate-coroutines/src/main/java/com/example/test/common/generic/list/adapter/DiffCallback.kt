package com.example.test.common.generic.list.adapter

import androidx.recyclerview.widget.DiffUtil

/**
 * The default implementation of [DiffUtil.ItemCallback] that efficiently updates
 * the items inside [GenericListAdapter].
 *
 * You can create your diff callback by extending [DiffCallback] If you want to change
 * the default items comparisons or generate payload objects.
 *
 * For example:
 * ```
 * class CustomDiffCallback : DiffCallback<Item>() {
 *      override fun getChangePayload(oldItem: Item, newItem: Item): Any? {
 *          // return payload
 *      }
 * }
 * ```
 */
open class DiffCallback<T : Item> : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem.identifier == newItem.identifier
    }

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem == newItem
    }
}