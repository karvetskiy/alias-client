package com.example.alias_client.net

import com.example.alias_client.data.Room
import io.reactivex.Observable
import okhttp3.ResponseBody

interface RequestsI {

    fun createRoom():Observable<Room>
    fun destroyRoom(roomid: Int):Observable<ResponseBody>
    fun addUser(roomid: Int):Observable<Int>
    fun deleteUser(roomid: Int, userid: Int):Observable<ResponseBody>
    fun activeUser(roomid: Int, userid: Int):Observable<Room>
    fun getRoomState(roomid: Int):Observable<Room>
    fun getWord():Observable<String>

}