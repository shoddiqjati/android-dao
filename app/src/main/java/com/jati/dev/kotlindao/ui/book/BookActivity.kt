package com.jati.dev.kotlindao.ui.book

import android.content.Intent
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import com.jati.dev.kotlindao.R
import com.jati.dev.kotlindao.base.BaseActivity
import com.jati.dev.kotlindao.common.GeneralRecyclerViewAdapter
import com.jati.dev.kotlindao.entity.Author
import com.jati.dev.kotlindao.entity.Book
import com.jati.dev.kotlindao.ui.author.AuthorActivity
import com.jati.dev.kotlindao.utils.toast
import kotlinx.android.synthetic.main.activity_book.*
import kotlinx.android.synthetic.main.item_book.view.*

class BookActivity : BaseActivity(), BookContract.View {

    private val addBookDialog = AddBookDialog()

    private val bookList = mutableListOf<Book>()
    private val bookAdapter by lazy {
        GeneralRecyclerViewAdapter(R.layout.item_book, bookList) { book, view ->
            view.apply {
                tv_title.text = book.title
                tv_author.text = book.author.target.name
            }
        }
    }

    override lateinit var presenter: BookContract.Presenter

    override val layoutId: Int = R.layout.activity_book

    override fun initPresenter() {
        presenter = BookPresenter(this, boxStore)
    }

    override fun initUiComponents() {
        setTitle(R.string.books)
        with(rv_book) {
            layoutManager = LinearLayoutManager(this@BookActivity)
            adapter = bookAdapter
            addItemDecoration(DividerItemDecoration(this@BookActivity, DividerItemDecoration.VERTICAL))
        }

        fab.setOnClickListener {
            addBookDialog.show(supportFragmentManager, addBookDialog.tag)
        }
    }

    override fun onUiReady() {
        presenter.loadAuthors()
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_books, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.action_delete -> presenter.emptyBooks()
            R.id.action_author -> startActivity(Intent(this, AuthorActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBookEmpty() {
        bookList.clear()
        bookAdapter.notifyDataSetChanged()
    }

    override fun showAuthors(authors: List<Author>) {
        addBookDialog.setCallback(authors, object : AddBookDialog.AddBookCallback {
            override fun onAddClicked(book: Book) {
                presenter.addBook(book)
            }
        })
    }
}
