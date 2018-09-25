package com.jati.dev.kotlindao.utils

import android.content.Context
import android.widget.Toast

/**
 * Created by Jati on 25/09/18.
 */

fun Context.toast(message: String, duration: Int = Toast.LENGTH_LONG) {
    Toast.makeText(this, message, duration).show()
}