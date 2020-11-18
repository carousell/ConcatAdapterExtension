package com.carousell.concatadapterextension

import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

interface SpanSizeLookupOwner {
    fun getSpanSizeLookup(): GridLayoutManager.SpanSizeLookup
}

class ConcatSpanSizeLookup(private val mergeAdapter: ConcatAdapter, private val spanCount: Int) :
    GridLayoutManager.SpanSizeLookup() {

    private val list: List<RecyclerView.Adapter<*>>
        get() = mergeAdapter.adapters

    override fun getSpanSize(position: Int): Int {
        var index = position
        list.forEach { adapter ->
            if (index < adapter.itemCount) {
                return if (adapter is SpanSizeLookupOwner) {
                    adapter.getSpanSizeLookup().getSpanSize(index)
                } else {
                    spanCount
                }
            } else {
                index -= adapter.itemCount
            }
        }
        return spanCount

    }
}