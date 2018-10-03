package com.jati.dev.kotlindao.entity

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.relation.ToOne

/**
 * Created by Jati on 25/09/18.
 */

@Entity
class Book {
    @Id
    var id: Long = 0
    lateinit var title: String
    lateinit var author: ToOne<Author>
}