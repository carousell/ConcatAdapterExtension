package com.carousell.concatadapterextension.app

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.carousell.concatadapterextension.ItemDecorationOwner
import com.carousell.concatadapterextension.SpanSizeLookupOwner
import kotlinx.android.synthetic.main.adapter_text.view.*

class BaseAdapter(
    private val size: Int,
    private val spanSizeLookup: GridLayoutManager.SpanSizeLookup,
    private val itemDecoration: RecyclerView.ItemDecoration
) : RecyclerView.Adapter<BaseAdapter.ViewHolder>(), SpanSizeLookupOwner, ItemDecorationOwner {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("SetTextI18n")
        fun bind(position: Int) {
            itemView.textView.text = "#$position"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.adapter_text, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount() = size

    override fun getSpanSizeLookup() = spanSizeLookup

    override fun getItemDecorations() = listOf(itemDecoration)
}