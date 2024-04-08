package com.example.test.common.generic.list.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

/**
 * This class is an implementation of [ListAdapter] that helps to build dynamic lists.
 *
 * A complete usage pattern would look like this:
 *
 * Implement [Item]:
 * ```
 * class ListItem(private val markableItem: MarkableItem): Item() {
 *      override val type = R.id.list_item
 *      override val identifier = markableItem.item.id().toLong()
 *
 *      class ViewHolder(private val binding: ItemBinding) : BindingViewHolder<ListItem>(binding) {
 *          override fun bind(item: ListItem) {
 *              //bind data here
 *          }
 *      }
 * }
 * ```
 * Implement [GenericListAdapter.ViewHolderFactory]:
 * ```
 * class ListViewHolderFactory : GenericListAdapter.ViewHolderFactory {
 *      override fun create(parent: ViewGroup, viewType: Int) {
 *          if (viewType == R.id.list_item) {
 *              return ListItem.ViewHolder();
 *          } else {
 *              throw IllegalStateException("Unidentified view type.")
 *          }
 *      }
 * }
 * ```
 * Define a custom diff callback if needed:
 * ```
 * class CustomDiffCallback : DiffCallback<ListItem>() {
 *      override fun getChangePayload(oldItem: ListItem, newItem: ListItem): Any? {
 *          // return payload
 *      }
 * }
 * ```
 * Set the adapter to the RecyclerView:
 * ```
 * @Inject
 * lateinit var viewHolderFactory: ListViewHolderFactory
 *
 * private val listAdapter by lazy(LazyThreadSafetyMode.NONE) {
 *      GenericListAdapter<Item>(trackViewHolderFactory)
 * }
 *
 * binding.list.adapter = listAdapter
 * ```
 *
 */
class GenericListAdapter<T : Item>(
    private val factory: ViewHolderFactory,
    diffCallback: DiffUtil.ItemCallback<T> = DiffCallback()
) : ListAdapter<T, RecyclerView.ViewHolder>(diffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        factory.create(parent, viewType)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.toBindingViewHolder().bind(getItem(position))
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isNotEmpty()) {
            holder.toBindingViewHolder().bind(getItem(position), payloads)
        } else {
            super.onBindViewHolder(holder, position, payloads)
        }
    }

    /**
     * Gets [Item] from the GenericListAdapter.
     *
     * @param position Adapter position to query
     * @return [Item]
     */
    fun getItemByPosition(position: Int): T {
        return getItem(position)
    }

    @Suppress("UNCHECKED_CAST")
    private fun RecyclerView.ViewHolder.toBindingViewHolder() = this as? BindingViewHolder<T>
        ?: this as? DefaultViewHolder<T>
        ?: throw IllegalStateException("ViewHolder must be a subtype of BindingViewHolder.")

    override fun getItemViewType(position: Int): Int {
        return getItem(position).type
    }

    override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
        super.onViewRecycled(holder)
        holder.toBindingViewHolder().unbind()
    }

    /**
     * An interface for creating [RecyclerView.ViewHolder] instances based on a viewType.
     */
    fun interface ViewHolderFactory {

        /**
         * Build concrete ViewHolders for given viewTypes.
         */
        fun create(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder
    }
}