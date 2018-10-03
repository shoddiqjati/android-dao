package com.jati.dev.kotlindao.ui.author

import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import com.jati.dev.kotlindao.R
import com.jati.dev.kotlindao.base.BaseActivity
import com.jati.dev.kotlindao.common.GeneralRecyclerViewAdapter
import com.jati.dev.kotlindao.entity.Author
import com.jati.dev.kotlindao.utils.toast
import kotlinx.android.synthetic.main.activity_author.*
import kotlinx.android.synthetic.main.item_book.view.*

/**
 * Created by Jati on 01/10/18.
 */

class AuthorActivity : BaseActivity(), AuthorContract.View {

    private val authorList = mutableListOf<Author>()

    private val authorAdapter by lazy {
        GeneralRecyclerViewAdapter(R.layout.item_book, authorList)
        { author, view ->
            view.apply {
                tv_title.text = author.name
                tv_author.text = if (author.sex == 1) getString(R.string.male) else getString(R.string.female)
                setOnClickListener { toast(author.books.size.toString()) }
            }
        }
    }

    private val addAuthorDialog = AddAuthorDialog()

    override val layoutId: Int = R.layout.activity_author

    override lateinit var presenter: AuthorContract.Presenter

    override fun initPresenter() {
        presenter = AuthorPresenter(this, boxStore)
    }

    override fun initUiComponents() {
        setTitle(R.string.author)
        addAuthorDialog.setCallback(object : AddAuthorDialog.AddAuthorCallback {
            override fun onAddClicked(author: Author) {
                addAuthor(author)
            }
        })

        with(rv_author) {
            val linearManager = LinearLayoutManager(this@AuthorActivity)
            layoutManager = linearManager
            addItemDecoration(DividerItemDecoration(this@AuthorActivity, DividerItemDecoration.VERTICAL))
            adapter = authorAdapter
        }

        fab.setOnClickListener {
            addAuthorDialog.show(supportFragmentManager, getString(R.string.author))
        }
    }

    override fun onUiReady() {
        presenter.loadAuthors()
    }

    override fun showAuthors(authors: List<Author>) {
        authorList.clear()
        authorList.addAll(authors)
        authorAdapter.notifyDataSetChanged()
    }

    override fun onAuthorAdded() {
        presenter.loadAuthors()
    }

    private fun addAuthor(author: Author) {
        presenter.addAuthor(author)
    }
}