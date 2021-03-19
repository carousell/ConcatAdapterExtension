package com.carousell.concatadapterextension

import android.graphics.Canvas
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

interface ItemDecorationOwner {
    fun getItemDecorations(): List<RecyclerView.ItemDecoration>
}

class ConcatItemDecoration(
    private val adaptersProvider: () -> List<RecyclerView.Adapter<*>>
) : RecyclerView.ItemDecoration() {

    private fun firstItemDecoration() =
        (adaptersProvider.invoke().firstOrNull() as ItemDecorationOwner?)
            ?.getItemDecorations()?.firstOrNull()

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)
        firstItemDecoration()?.onDraw(c, parent, state)
    }

    override fun onDraw(c: Canvas, parent: RecyclerView) {
        super.onDraw(c, parent)
        firstItemDecoration()?.onDraw(c, parent)
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)
        firstItemDecoration()?.onDrawOver(c, parent, state)
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView) {
        super.onDrawOver(c, parent)
        firstItemDecoration()?.onDrawOver(c, parent)
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        var index = parent.getChildAdapterPosition(view)
        adaptersProvider.invoke().forEach { adapter ->
            if (index < adapter.itemCount) {
                if (adapter is ItemDecorationOwner) {
                    applyOffset(adapter.getItemDecorations(), outRect, view, parent, state)
                    return
                } else {
                    outRect.set(0, 0, 0, 0)
                    return
                }
            } else {
                index -= adapter.itemCount
            }
        }
        outRect.set(0, 0, 0, 0)
    }

    private fun applyOffset(
        list: List<RecyclerView.ItemDecoration>,
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        if (list.size == 1) {//fast path
            list[0].getItemOffsets(outRect, view, parent, state)
        } else {
            list.forEach {
                val innerRect = Rect()
                it.getItemOffsets(innerRect, view, parent, state)
                outRect.top += innerRect.top
                outRect.bottom += innerRect.bottom
                outRect.left += innerRect.left
                outRect.right += innerRect.right
            }
        }
    }
}