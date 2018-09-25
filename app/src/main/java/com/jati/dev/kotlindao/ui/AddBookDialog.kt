package com.jati.dev.kotlindao.ui

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jati.dev.kotlindao.R
import com.jati.dev.kotlindao.entity.Book
import com.jati.dev.kotlindao.utils.toast
import kotlinx.android.synthetic.main.dialog_add_book.*

/**
 * Created by Jati on 25/09/18.
 */

class AddBookDialog : DialogFragment() {

    private lateinit var callback: AddBookCallback

    interface AddBookCallback {
        fun onAddClicked(book: Book)
    }

    fun setCallback(callback: AddBookCallback) {
        this.callback = callback
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.ThemeOverlay_AppCompat_Dialog_Alert)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.dialog_add_book, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_add.setOnClickListener {
            if (et_title.text.toString().trim().isNotEmpty() && et_author.text.toString().trim().isNotEmpty()) {
                callback.onAddClicked(Book(title = et_title.text.toString(), author = et_author.text.toString()))
                dismiss()
            } else context?.toast(getString(R.string.please_complete))
        }
    }

    override fun dismiss() {
        super.dismiss()
        et_title.text.clear()
        et_author.text.clear()
    }
}