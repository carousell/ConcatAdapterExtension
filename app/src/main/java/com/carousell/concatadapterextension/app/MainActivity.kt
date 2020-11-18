package com.carousell.concatadapterextension.app

import android.os.Bundle
import android.util.DisplayMetrics
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.GridLayoutManager
import com.carousell.concatadapterextension.ConcatSpaceItemDecoration
import com.carousell.concatadapterextension.ConcatSpanSizeLookup
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private fun Int.toPixel(): Int {
        return this * resources.displayMetrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val spanCount = 4
        val adapter = ConcatAdapter(
            BaseAdapter(
                4,
                SimpleSpanSizeLookup(1),
                SimpleItemDecoration(8.toPixel())
            ),
            BaseAdapter(
                4,
                SimpleSpanSizeLookup(1),
                SimpleItemDecoration(16.toPixel())
            ),
            BaseAdapter(
                4,
                SimpleSpanSizeLookup(2),
                SimpleItemDecoration(8.toPixel())
            ),
            BaseAdapter(
                4,
                SimpleSpanSizeLookup(4),
                SimpleItemDecoration(8.toPixel())
            )
        )

        recyclerView.layoutManager =
            GridLayoutManager(this, spanCount, GridLayoutManager.VERTICAL, false).also {
                it.spanSizeLookup = ConcatSpanSizeLookup(adapter, spanCount)
            }
        recyclerView.addItemDecoration(ConcatSpaceItemDecoration(adapter))
        recyclerView.adapter = adapter
    }
}