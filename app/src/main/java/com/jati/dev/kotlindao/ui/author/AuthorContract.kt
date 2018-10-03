package com.jati.dev.kotlindao.ui.author

import com.jati.dev.kotlindao.base.BasePresenter
import com.jati.dev.kotlindao.base.BaseView
import com.jati.dev.kotlindao.entity.Author

/**
 * Created by Jati on 01/10/18.
 */

interface AuthorContract {

    interface View : BaseView<Presenter> {
        fun showAuthors(authors: List<Author>)
        fun onAuthorAdded()
    }

    interface Presenter : BasePresenter {
        fun loadAuthors()
        fun addAuthor(author: Author)
    }
}