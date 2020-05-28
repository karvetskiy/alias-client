package com.example.alias_client.net

import com.example.alias_client.MainActivity.Companion.room
import com.example.alias_client.MainActivity.Companion.user
import com.example.alias_client.MainActivity.Companion.winnerid
import com.example.alias_client.MainActivity.Companion.word
import com.example.alias_client.data.RequestBody

class Converter {

    private val requests: RequestsI = RequestsImpl()

    fun getWord(onWordGet:() -> Unit){
        requests
            .getWord()
            .subscribe({
                word = it
                onWordGet.invoke()
            }, {
                it.printStackTrace()
            })
    }

    fun updateRoomState(roomid: Int, onUpdatedRoom:() -> Unit){
        requests
            .getRoomState(roomid)
            .subscribe({
                room = it
                room.users.find { it.userid == user.userid }?.let { user = it }
                onUpdatedRoom.invoke()
            }, {
                it.printStackTrace()
            })

    }

    fun addUser(roomid: Int, onAddedUser:() -> Unit){
        requests
            .addUser(roomid)
            .subscribe({
                user.userid = it
                onAddedUser.invoke()
            }, {
                it.printStackTrace()
            })
    }

    fun activeUser(onCreatedRoom: () -> Unit){
        requests
            .activeUser(room.roomid, user.userid)
            .subscribe({
                room = it
                onCreatedRoom.invoke()
            }, {
                it.printStackTrace()
            })
    }

    fun createRoom(onCreatedRoom:() -> Unit){
        requests
            .createRoom()
            .subscribe({
                room = it
                onCreatedRoom.invoke()
            }, {
                it.printStackTrace()
            })
    }


    fun nextUser(onUserChanged:() -> Unit){
        val body = RequestBody(room.roomid)
        requests
            .nextUser(body)
            .subscribe({
                onUserChanged.invoke()
            }, {it.printStackTrace()})
    }

    fun update(onUpdated:() -> Unit){
        val body = RequestBody(room.roomid, user.userid, user.score, user.username)
        requests
            .update(body)
            .subscribe({
                onUpdated.invoke()
            },{it.printStackTrace()})
    }

    fun deleteUser(onDeletedUser:() -> Unit){
        val body = RequestBody(room.roomid, user.userid)
        requests
            .deleteUser(body)
            .subscribe({
                onDeletedUser.invoke()
            }, {it.printStackTrace()})
    }

    fun getWinner(onGetWinner:() -> Unit){
        requests
            .winner(room.roomid)
            .subscribe({
                winnerid = it
                onGetWinner.invoke()
            }, {
                it.printStackTrace()
            })
    }

    fun start(onStarted:() -> Unit){
        val body = RequestBody(room.roomid)
        requests
            .start(body)
            .subscribe({
                onStarted.invoke()
            }, {it.printStackTrace()})
    }

    fun end(onEnded:() -> Unit){
        val body = RequestBody(room.roomid)
        requests
            .end(body)
            .subscribe({
                onEnded.invoke()
            }, {it.printStackTrace()})
    }

}