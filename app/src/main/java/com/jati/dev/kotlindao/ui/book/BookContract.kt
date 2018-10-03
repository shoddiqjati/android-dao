package com.jati.dev.kotlindao.ui.book

import com.jati.dev.kotlindao.base.BasePresenter
import com.jati.dev.kotlindao.base.BaseView
import com.jati.dev.kotlindao.entity.Author
import com.jati.dev.kotlindao.entity.Book

/**
 * Created by Jati on 25/09/18.
 */

interface BookContract {

    interface View : BaseView<Presenter> {
        fun addSuccess()

        fun showBooks(books: List<Book>)

        fun onBookEmpty()

        fun showAuthors(authors: List<Author>)
    }

    interface Presenter : BasePresenter {
        fun addBook(book: Book)

        fun loadBooks()

        fun emptyBooks()

        fun loadAuthors()
    }
}