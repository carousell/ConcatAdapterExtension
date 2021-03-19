package com.carousell.concatadapterextension.app

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class SimpleItemDecoration(private val size: Int) : RecyclerView.ItemDecoration() {
    private val paint = Paint().also {
        it.color = Color.RED
        it.strokeWidth = size.toFloat()
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)
        drawBottom(c, parent)
    }

    private fun drawBottom(c: Canvas, parent: RecyclerView) {
        val left = parent.paddingLeft.toFloat()
        val right = parent.width - parent.paddingRight.toFloat()
        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)
            val params = child.layoutParams as RecyclerView.LayoutParams
            val top = child.bottom + params.bottomMargin +
                    child.translationY
            val bottom = top + size
            c.drawRect(left, top, right, bottom, paint)
        }
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.set(size, size, size, size)
    }

}