package com.jati.dev.kotlindao.ui.author

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jati.dev.kotlindao.R
import com.jati.dev.kotlindao.entity.Author
import com.jati.dev.kotlindao.utils.toast
import kotlinx.android.synthetic.main.dialog_add_author.*

/**
 * Created by Jati on 25/09/18.
 */

class AddAuthorDialog : DialogFragment() {

    private lateinit var callback: AddAuthorCallback

    interface AddAuthorCallback {
        fun onAddClicked(author: Author)
    }

    fun setCallback(callback: AddAuthorCallback) {
        this.callback = callback
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.ThemeOverlay_AppCompat_Dialog_Alert)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.dialog_add_author, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_add.setOnClickListener {
            if (et_author.text.toString().trim().isNotEmpty()) {
                callback.onAddClicked(Author().apply { name = et_author.text.toString() })
                dismiss()
            } else context?.toast(getString(R.string.please_complete))
        }
    }

    override fun dismiss() {
        super.dismiss()
        et_author.text.clear()
    }
}