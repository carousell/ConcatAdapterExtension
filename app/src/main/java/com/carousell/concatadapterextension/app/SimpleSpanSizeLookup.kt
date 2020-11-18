package com.carousell.concatadapterextension.app

import androidx.recyclerview.widget.GridLayoutManager

class SimpleSpanSizeLookup(private val size: Int) : GridLayoutManager.SpanSizeLookup() {
    override fun getSpanSize(position: Int): Int {
        return size
    }
}