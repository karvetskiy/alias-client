package com.example.alias_client.net

import android.widget.Toast
import com.example.alias_client.MainActivity
import com.example.alias_client.MainActivity.Companion.winner
import com.example.alias_client.MainActivity.Companion.word
import com.example.alias_client.RoomActivity

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

    fun updateRoomState(roomId: Int, onUpdatedRoom:() -> Unit){
        requests
            .getRoomState(roomId)
            .subscribe({
                MainActivity.room = it
                onUpdatedRoom.invoke()
            }, {
                it.printStackTrace()
            })

    }

    fun addUser(roomid: Int, onAddedUser:() -> Unit){
        requests
            .addUser(roomid)
            .subscribe({
                MainActivity.user.userid = it
                onAddedUser.invoke()
            }, {
                it.printStackTrace()
            })
    }

    fun activeUser(onCreatedRoom: () -> Unit){
        requests
            .activeUser(MainActivity.room.roomid, MainActivity.user.userid)
            .subscribe({
                MainActivity.room = it
                onCreatedRoom.invoke()
            }, {
                it.printStackTrace()
            })
    }

    fun createRoom(onCreatedRoom:() -> Unit){
        requests
            .createRoom()
            .subscribe({
                MainActivity.room = it
                onCreatedRoom.invoke()
            }, {
                it.printStackTrace()
            })
    }


    fun nextUser(onUserChanged:() -> Unit){
        requests
            .nextUser(MainActivity.room.roomid, MainActivity.user.userid)
            .subscribe({
                onUserChanged.invoke()
            }, {it.printStackTrace()})
    }

    fun update(onUpdated:() -> Unit){
        requests
            .update(MainActivity.room.roomid, MainActivity.user.userid, MainActivity.user.score)
            .subscribe({
                onUpdated.invoke()
            },{it.printStackTrace()})
    }

    fun deleteUser(onDeletedUser:() -> Unit){
        requests
            .deleteUser(MainActivity.room.roomid, MainActivity.user.userid)
            .subscribe({
                onDeletedUser.invoke()
            }, {it.printStackTrace()})
    }

    fun getWinner(onGetWinner:() -> Unit){
        requests
            .winner(MainActivity.room.roomid)
            .subscribe({
                winner = it
                onGetWinner.invoke()
            }, {
                it.printStackTrace()
            })
    }

}