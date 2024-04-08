package com.example.test.common.generic.list.adapter

import android.graphics.Canvas
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * An item decoration that allows adding of sticky headers to [RecyclerView].
 */
abstract class AbstractItemDecoration(private val listener: StickyHeaderInterface) :
    RecyclerView.ItemDecoration() {

    var mStickyHeaderHeight: Int = -1

    override fun onDrawOver(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(canvas, parent, state)
        disableClicksForHeaders(parent)
        val topChild = parent.getChildAt(0) ?: return

        val topChildPosition = parent.getChildAdapterPosition(topChild)
        if (topChildPosition == RecyclerView.NO_POSITION) {
            return
        }

        val headerPos: Int = listener.getHeaderPositionForItem(topChildPosition)
        val currentHeader: View = getHeaderViewForItem(headerPos, parent)
        fixLayoutSize(parent, currentHeader)
        val contactPoint = currentHeader.bottom + currentHeader.paddingBottom
        val childInContact: View? = getChildInContact(parent, contactPoint, headerPos)

        if (childInContact != null && listener.isHeader(parent.getChildAdapterPosition(childInContact))) {
            moveHeader(canvas, currentHeader, childInContact)
            return
        }

        drawHeader(canvas, currentHeader)
    }

    private fun disableClicksForHeaders(recyclerView: RecyclerView) {
        recyclerView.addOnItemTouchListener(object : RecyclerView.OnItemTouchListener {
            override fun onInterceptTouchEvent(rv: RecyclerView, motionEvent: MotionEvent): Boolean {
                if (motionEvent.y <= mStickyHeaderHeight) {
                    return true
                }
                return false
            }
            override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {}
            override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {}
        })
    }

    abstract fun getHeaderViewForItem(headerPosition: Int, parent: RecyclerView): View

    abstract fun getChildInContact(parent: RecyclerView, contactPoint: Int, currentHeaderPos: Int): View?

    abstract fun moveHeader(c: Canvas, currentHeader: View, nextHeader: View)

    abstract fun drawHeader(c: Canvas, header: View)

    private fun fixLayoutSize(parent: ViewGroup, view: View){
        val widthSpec = View.MeasureSpec.makeMeasureSpec(parent.width, View.MeasureSpec.EXACTLY)
        val heightSpec =
            View.MeasureSpec.makeMeasureSpec(parent.height, View.MeasureSpec.UNSPECIFIED)

        val childWidthSpec = ViewGroup.getChildMeasureSpec(
            widthSpec,
            parent.paddingLeft + parent.paddingRight,
            view.layoutParams.width
        )
        val childHeightSpec = ViewGroup.getChildMeasureSpec(
            heightSpec,
            parent.paddingTop + parent.paddingBottom,
            view.layoutParams.height
        )
        view.measure(childWidthSpec, childHeightSpec)
        view.layout(0, 0, view.measuredWidth, view.measuredHeight.also {
            mStickyHeaderHeight = it
        })
    }
}