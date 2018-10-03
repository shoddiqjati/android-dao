package com.jati.dev.kotlindao.ui.author

import com.jati.dev.kotlindao.entity.Author
import io.objectbox.Box
import io.objectbox.BoxStore

/**
 * Created by Jati on 01/10/18.
 */

class AuthorPresenter(private val view: AuthorContract.View,
                      private val boxStore: BoxStore) : AuthorContract.Presenter {

    private val authorBox: Box<Author> = boxStore.boxFor(Author::class.java)

    init {
        view.presenter = this
    }

    override fun loadAuthors() {
        view.showAuthors(authorBox.all)
    }

    override fun addAuthor(author: Author) {
        authorBox.put(author)
        view.onAuthorAdded()
    }
}