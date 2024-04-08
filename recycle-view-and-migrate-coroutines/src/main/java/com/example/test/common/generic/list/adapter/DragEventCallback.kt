package com.example.test.common.generic.list.adapter

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.NO_POSITION
import java.util.Collections

private const val DisableFlag = 0

/**
 * Provides a base implementation for [ItemTouchHelper.Callback] for the given drag allowance.
 */
abstract class DragEventCallback(protected val adapter: GenericListAdapter<Item>) :
    ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP or ItemTouchHelper.DOWN, 0) {
    private var fromPosition = NO_POSITION
    private var toPosition = NO_POSITION

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        val currentItem = adapter.getItemByPosition(target.adapterPosition)
        return if (currentItem is Draggable && currentItem.isDraggable) {
            fromPosition = viewHolder.adapterPosition
            updateList(viewHolder, target)
            true
        } else {
            false
        }
    }

    private fun updateList(viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder) {
        val items = adapter.currentList.toMutableList()
        Collections.swap(items, target.adapterPosition, viewHolder.adapterPosition)
        adapter.submitList(items)
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) = Unit

    override fun onMoved(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        fromPos: Int,
        target: RecyclerView.ViewHolder,
        toPos: Int,
        x: Int,
        y: Int
    ) {
        super.onMoved(recyclerView, viewHolder, fromPos, target, toPos, x, y)
        toPosition = toPos
    }

    override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
        super.onSelectedChanged(viewHolder, actionState)
        when (actionState) {
            ItemTouchHelper.ACTION_STATE_DRAG -> onDragEvent(viewHolder)
            ItemTouchHelper.ACTION_STATE_IDLE -> {
                if (fromPosition != NO_POSITION && toPosition == NO_POSITION) {
                    toPosition = fromPosition
                }
                onItemDropped()
            }
        }
    }

    private fun onItemDropped() {
        if(fromPosition != toPosition) {
            notifyItemDropped(fromPosition, toPosition)
            resetPositions()
        }
    }

    private fun resetPositions() {
        fromPosition = NO_POSITION
        toPosition = NO_POSITION
    }

    /**
     * Callback for whenever an item that is being dragged exchanges positions with another one.
     */
    protected abstract fun onDragEvent(viewHolder: RecyclerView.ViewHolder?)

    /**
     * Callback for when the drag & drop event has completed because the user has dropped the item.
     */
    protected abstract fun onDropEvent(viewHolder: RecyclerView.ViewHolder)

    /**
     * Callback for when the item has dropped and successfully changed position.
     */
    protected abstract fun notifyItemDropped(fromPosition: Int, toPosition: Int)

    override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) =
        when {
            viewHolder.isNotDraggable() -> DisableFlag
            else -> super.getMovementFlags(recyclerView, viewHolder)
        }

    /**
     * Determines whether the given [RecyclerView.ViewHolder] can be dragged.
     */
    protected abstract fun RecyclerView.ViewHolder.isNotDraggable(): Boolean

    override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
        super.clearView(recyclerView, viewHolder)
        onDropEvent(viewHolder)
    }
}