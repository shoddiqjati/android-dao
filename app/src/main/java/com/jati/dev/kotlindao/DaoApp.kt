package com.jati.dev.kotlindao

import android.app.Application
import com.jati.dev.kotlindao.entity.MyObjectBox
import io.objectbox.BoxStore
import io.objectbox.android.AndroidObjectBrowser

/**
 * Created by Jati on 25/09/18.
 */

class DaoApp : Application() {

    private lateinit var boxStore: BoxStore

    override fun onCreate() {
        super.onCreate()

        boxStore = MyObjectBox.builder().androidContext(this).build()
        if (BuildConfig.DEBUG) AndroidObjectBrowser(boxStore).start(this)
    }

    fun getBoxStore() : BoxStore = boxStore
}