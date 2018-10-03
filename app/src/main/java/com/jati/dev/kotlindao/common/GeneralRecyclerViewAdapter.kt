package com.jati.dev.kotlindao.common

import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by Jati on 01/10/18.
 */

class GeneralRecyclerViewAdapter<T>(@LayoutRes val layoutId: Int,
                                    private val room: List<T>,
                                    private val bindAction: (T, View) -> Unit
) : RecyclerView.Adapter<GeneralRecyclerViewAdapter.GeneralViewHolder<T>>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GeneralViewHolder<T> =
            GeneralViewHolder(LayoutInflater.from(parent.context).inflate(layoutId, parent, false))

    override fun getItemCount(): Int = room.size

    override fun onBindViewHolder(holder: GeneralViewHolder<T>, position: Int) {
        holder.bind(room[position], bindAction)
    }

    class GeneralViewHolder<T>(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(any: T, bindAction: (T, View) -> Unit) = bindAction(any, itemView)
    }
}