package com.jati.dev.kotlindao.ui.book

import com.jati.dev.kotlindao.entity.Author
import com.jati.dev.kotlindao.entity.Author_
import com.jati.dev.kotlindao.entity.Book
import com.jati.dev.kotlindao.entity.Book_
import io.objectbox.Box
import io.objectbox.BoxStore

/**
 * Created by Jati on 25/09/18.
 */

class BookPresenter(private val view: BookContract.View,
                    store: BoxStore) : BookContract.Presenter {

    private val bookBox: Box<Book> = store.boxFor(Book::class.java)
    private val authorBox: Box<Author> = store.boxFor(Author::class.java)

    init {
        view.presenter = this
    }

    override fun addBook(book: Book) {
        bookBox.put(book)
        view.addSuccess()
        loadBooks()
    }

    override fun loadBooks() {
        view.showBooks(bookBox.query().orderDesc(Book_.__ID_PROPERTY).build().find())
    }

    override fun emptyBooks() {
        bookBox.removeAll()
        view.onBookEmpty()
    }

    override fun loadAuthors() {
        view.showAuthors(authorBox.query().orderDesc(Author_.__ID_PROPERTY).build().find())
    }
}