package com.jati.dev.kotlindao.ui.book

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.jati.dev.kotlindao.R
import com.jati.dev.kotlindao.entity.Author
import com.jati.dev.kotlindao.entity.Book
import com.jati.dev.kotlindao.utils.toast
import kotlinx.android.synthetic.main.dialog_add_book.*

/**
 * Created by Jati on 25/09/18.
 */

class AddBookDialog : DialogFragment() {

    private lateinit var callback: AddBookCallback
    private lateinit var authorList: List<Author>
    private lateinit var authorAdapter: ArrayAdapter<String>

    interface AddBookCallback {
        fun onAddClicked(book: Book)
    }

    fun setCallback(list: List<Author>, callback: AddBookCallback) {
        this.callback = callback
        this.authorList = list
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.ThemeOverlay_AppCompat_Dialog_Alert)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.dialog_add_book, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        context?.let { context ->
            authorAdapter = ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, authorList.map { it.name })
            sp_author.adapter = authorAdapter
            sp_author.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {
                    parent?.setSelection(0)
                }

                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    parent?.setSelection(position)
                }
            }

            btn_add.setOnClickListener {
                if (et_title.text.toString().trim().isNotEmpty() && null != sp_author.selectedItem) {
                    callback.onAddClicked(Book().apply {
                        title = et_title.text.toString()
                        author.target = authorList[sp_author.selectedItemPosition]
                    })
                    dismiss()
                } else context.toast(getString(R.string.please_complete))
            }
        }
    }

    override fun dismiss() {
        super.dismiss()
        et_title.text.clear()
    }
}