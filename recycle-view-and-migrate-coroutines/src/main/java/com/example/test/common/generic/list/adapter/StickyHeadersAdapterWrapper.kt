package com.example.test.common.generic.list.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * A wrapper class that adds sticky headers support to [GenericListAdapter].
 */
abstract class StickyHeadersAdapterWrapper<T : Item> :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(), StickyHeaderInterface {
    private var adapter: GenericListAdapter<T>? = null
    var headerPosition = 0

    override fun getHeaderPositionForItem(itemPosition: Int): Int {
        var itemPositions = itemPosition
        do {
            if (isHeader(itemPositions)) {
                headerPosition = itemPositions
                break
            }
            itemPositions -= 1
        } while (itemPositions >= 0)
        return headerPosition
    }

    override fun getHeaderLayout(headerPosition: Int): Int =
        getItem(headerPosition)?.identifier?.toInt() ?: -1

    override fun isHeader(itemPosition: Int) =
        (getItem(itemPosition) as? Taggable)?.tag == Tags.Header

    /**
     * Wraps the [GenericListAdapter] with this this adapter wrapper and keep its reference
     * to forward all events correctly.
     *
     * @param adapter The GenericListAdapter which contains the base logic
     * @return [GenericListAdapter]
     */
    fun wrap(adapter: GenericListAdapter<T>): StickyHeadersAdapterWrapper<T> {
        this.adapter = adapter
        return this
    }

    /**
     * Overwrites the registerAdapterDataObserver to correctly forward all events
     * to the GenericListAdapter.
     *
     * @param observer Observer to register
     */
    override fun registerAdapterDataObserver(observer: RecyclerView.AdapterDataObserver) {
        super.registerAdapterDataObserver(observer)
        adapter?.registerAdapterDataObserver(observer)
    }

    /**
     * Overwrites the unregisterAdapterDataObserver to correctly forward all events
     * to the GenericListAdapter.
     *
     * @param observer Observer to unregister
     */
    override fun unregisterAdapterDataObserver(observer: RecyclerView.AdapterDataObserver) {
        super.unregisterAdapterDataObserver(observer)
        adapter?.unregisterAdapterDataObserver(observer)
    }

    /**
     * Overwrites the getItemViewType to correctly return the value from the GenericListAdapter
     *
     * @param position position to query
     * @return Integer value identifying the type of the view needed
     * to represent the item at position.
     */
    override fun getItemViewType(position: Int) = adapter?.getItemViewType(position) ?: 0

    /**
     * Overwrites the getItemId to correctly return the value from the GenericListAdapter
     *
     * @param position Adapter position to query
     * @return The stable ID of the item at position
     */
    override fun getItemId(position: Int) = adapter?.getItemId(position) ?: 0

    /**
     * Gets [Item] from the GenericListAdapter.
     *
     * @param position Adapter position to query
     * @return [Item]
     */
    fun getItem(position: Int): T? = adapter?.getItemByPosition(position)

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    override fun getItemCount() = adapter?.itemCount ?: 0

    /**
     * Calls when RecyclerView needs a new RecyclerView.ViewHolder
     * of the given type to represent an item.
     *
     * @param parent The ViewGroup into which the new View will be added
     * after it is bound to an adapter position.
     *
     * @param viewType The view type of the new View
     * @return A new ViewHolder that holds a View of the given view type.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        adapter?.onCreateViewHolder(parent, viewType)
            ?: throw RuntimeException("A adapter needs to be wrapped")

    /**
     * Calls by RecyclerView to display the data at the specified position.
     *
     * @param holder The ViewHolder which should be updated to represent the contents of the
     * item at the given position in the data set.
     *
     * @param position The position of the item within the adapter's data set.
     */
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        adapter?.onBindViewHolder(holder, position)
    }

    /**
     * Calls by RecyclerView to display the data at the specified position.
     *
     * @param holder The ViewHolder which should be updated to represent the contents of the
     * item at the given position in the data set.
     *
     * @param position The position of the item within the adapter's data set
     *
     * @param payloads A non-null list of merged payloads. Can be empty list if requires full update.
     */
    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
        payloads: List<Any>
    ) {
        adapter?.onBindViewHolder(holder, position, payloads.toMutableList())
    }

    /**
     * Indicates whether each item in the data set can be represented with a unique identifier
     * of type Long.
     *
     * @param hasStableIds Whether items in data set have unique identifiers or not.
     */
    override fun setHasStableIds(hasStableIds: Boolean) {
        adapter?.setHasStableIds(hasStableIds)
    }

    /**
     * Calls when a view created by this adapter has been recycled.
     *
     * @param holder The ViewHolder for the view being recycled
     */
    override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
        adapter?.onViewRecycled(holder)
    }

    /**
     * Calls by the RecyclerView if a ViewHolder created by this Adapter cannot be recycled
     * due to its transient state.
     *
     * @param holder The ViewHolder containing the View that could not be recycled
     * due to its transient state.
     * @return True if the View should be recycled, false otherwise
     */
    override fun onFailedToRecycleView(holder: RecyclerView.ViewHolder) =
        adapter?.onFailedToRecycleView(holder) ?: false

    /**
     * Calls when a view created by this adapter has been detached from its window.
     *
     * @param holder Holder of the view being detached
     */
    override fun onViewDetachedFromWindow(holder: RecyclerView.ViewHolder) {
        adapter?.onViewDetachedFromWindow(holder)
    }

    /**
     * Calls when a view created by this adapter has been attached to a window.
     *
     * @param holder Holder of the view being attached
     */
    override fun onViewAttachedToWindow(holder: RecyclerView.ViewHolder) {
        adapter?.onViewAttachedToWindow(holder)
    }

    /**
     * Calls by RecyclerView when it starts observing this Adapter.
     *
     * @param recyclerView The RecyclerView instance which started observing this adapter.
     */
    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        adapter?.onAttachedToRecyclerView(recyclerView)
    }

    /**
     * Calls by RecyclerView when it stops observing this Adapter.
     *
     * @param recyclerView The RecyclerView instance which stopped observing this adapter.
     */
    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        adapter?.onDetachedFromRecyclerView(recyclerView)
    }
}