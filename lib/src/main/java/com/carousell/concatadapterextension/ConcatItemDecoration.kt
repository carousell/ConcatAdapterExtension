package com.carousell.concatadapterextension

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.RecyclerView

interface ItemDecorationOwner {
    fun getItemDecorations(): List<RecyclerView.ItemDecoration>
}

class ConcatItemDecoration(private val mergeAdapter: ConcatAdapter) :
    RecyclerView.ItemDecoration() {

    private val list: List<RecyclerView.Adapter<*>>
        get() = mergeAdapter.adapters

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        var index = parent.getChildAdapterPosition(view)
        list.forEach { adapter ->
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