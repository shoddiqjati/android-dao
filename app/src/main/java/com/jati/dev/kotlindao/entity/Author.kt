package com.jati.dev.kotlindao.entity

import io.objectbox.annotation.Backlink
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

/**
 * Created by Jati on 01/10/18.
 */

@Entity
class Author {
    @Id var id: Long = 0
    lateinit var name: String
    @Backlink
    lateinit var books: List<Book>
}