package com.jati.dev.kotlindao.ui

import com.jati.dev.kotlindao.entity.Book

/**
 * Created by Jati on 25/09/18.
 */

interface BookContract {

    interface View {
        var presenter: Presenter

        fun addSuccess()

        fun showBooks(books: List<Book>)
    }

    interface Presenter {
        fun addBook(book: Book)

        fun loadBooks()
    }
}