package com.jati.dev.kotlindao.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import com.jati.dev.kotlindao.DaoApp
import com.jati.dev.kotlindao.R
import com.jati.dev.kotlindao.entity.Book
import com.jati.dev.kotlindao.utils.toast
import kotlinx.android.synthetic.main.activity_main.*

class BookActivity : AppCompatActivity(), BookContract.View {

    private val addBookDialog = AddBookDialog()

    private val bookList = mutableListOf<Book>()
    private val bookAdapter by lazy { BookAdapter(bookList) }

    override lateinit var presenter: BookContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val boxStore = (application as DaoApp).getBoxStore()
        presenter = BookPresenter(this, boxStore)

        initUiComponents()
    }

    private fun initUiComponents() {
        with(rv_book) {
            layoutManager = LinearLayoutManager(this@BookActivity)
            adapter = bookAdapter
            addItemDecoration(DividerItemDecoration(this@BookActivity, DividerItemDecoration.VERTICAL))
        }

        addBookDialog.setCallback(object : AddBookDialog.AddBookCallback {
            override fun onAddClicked(book: Book) {
                presenter.addBook(book)
            }
        })

        fab.setOnClickListener {
            addBookDialog.show(supportFragmentManager, addBookDialog.tag)
        }

        presenter.loadBooks()
    }

    override fun addSuccess() {
        toast("Success")
    }

    override fun showBooks(books: List<Book>) {
        bookList.clear()
        bookList.addAll(books)
        bookAdapter.notifyDataSetChanged()
    }
}
