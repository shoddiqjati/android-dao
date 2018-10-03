package com.jati.dev.kotlindao.base

/**
 * Created by Jati on 01/10/18.
 */

interface BaseView<T : BasePresenter> {
    var presenter: T
}