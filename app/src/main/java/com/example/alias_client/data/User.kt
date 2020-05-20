package dev.karvetskiy

import java.util.*


class User {
    lateinit var userid: Integer
    var score: Int = 0
    var active: Boolean = false

    init {
        createUserID()
    }

    fun createUserID(){
        userid = (10000 + Random().nextInt(90000)) as Integer
    }
}