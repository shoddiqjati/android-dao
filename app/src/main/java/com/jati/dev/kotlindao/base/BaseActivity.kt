package com.jati.dev.kotlindao.base

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.jati.dev.kotlindao.DaoApp
import io.objectbox.BoxStore

/**
 * Created by Jati on 01/10/18.
 */

@SuppressLint("Registered")
abstract class BaseActivity : AppCompatActivity() {

    lateinit var boxStore: BoxStore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)
        boxStore = (application as DaoApp).getBoxStore()
        initPresenter()
        initUiComponents()
        onUiReady()
    }

    abstract val layoutId: Int

    abstract fun initPresenter()

    abstract fun initUiComponents()

    abstract fun onUiReady()
}