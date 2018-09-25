package com.jati.dev.kotlindao.ui

import com.jati.dev.kotlindao.entity.Book
import com.jati.dev.kotlindao.entity.Book_
import io.objectbox.Box
import io.objectbox.BoxStore

/**
 * Created by Jati on 25/09/18.
 */

class BookPresenter(private val view: BookContract.View,
                    private val store: BoxStore) : BookContract.Presenter {

    private var bookBox: Box<Book>

    init {
        view.presenter = this
        bookBox = store.boxFor(Book::class.java)
    }

    override fun addBook(book: Book) {
        bookBox.put(book)
        view.addSuccess()
        loadBooks()
    }

    override fun loadBooks() {
        view.showBooks(bookBox.query().orderDesc(Book_.__ID_PROPERTY).build().find())
    }
}