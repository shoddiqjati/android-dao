package com.jati.dev.kotlindao.entity

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

/**
 * Created by Jati on 25/09/18.
 */

@Entity
data class Book(
        @Id
        var id: Long? = null,
        var title: String,
        var author: String
)