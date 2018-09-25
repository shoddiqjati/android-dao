package com.jati.dev.kotlindao.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jati.dev.kotlindao.R
import com.jati.dev.kotlindao.entity.Book
import kotlinx.android.synthetic.main.item_book.view.*

/**
 * Created by Jati on 25/09/18.
 */

class BookAdapter(private val room: List<Book>) : RecyclerView.Adapter<BookAdapter.BookHolder>() {
    override fun onCreateViewHolder(container: ViewGroup, viewType: Int): BookHolder =
            BookHolder(LayoutInflater.from(container.context).inflate(R.layout.item_book, container, false))

    override fun getItemCount(): Int = room.size

    override fun onBindViewHolder(holder: BookHolder, position: Int) {
        holder.bindBook(room[position])
    }

    class BookHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindBook(book: Book) {
            itemView.apply {
                tv_title.text = book.title
                tv_author.text = book.author
            }
        }
    }
}