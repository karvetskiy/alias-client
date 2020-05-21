package com.example.alias_client.data

import java.util.*
import kotlin.collections.ArrayList

class Room {
    var roomid = 0
    val users = ArrayList<User>()
    var activeUserID = 0

    init {
        createRoomID()
    }

    private fun createRoomID(){
        roomid =(1000 + Random().nextInt(9000))
    }


}