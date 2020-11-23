package com.carousell.concatadapterextension

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

interface SpanSizeLookupOwner {
    fun getSpanSizeLookup(): GridLayoutManager.SpanSizeLookup
}

class ConcatSpanSizeLookup(
    private val adaptersProvider: () -> List<RecyclerView.Adapter<*>>,
    private val spanCount: Int
) :
    GridLayoutManager.SpanSizeLookup() {

    override fun getSpanSize(position: Int): Int {
        var index = position
        adaptersProvider.invoke().forEach { adapter ->
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