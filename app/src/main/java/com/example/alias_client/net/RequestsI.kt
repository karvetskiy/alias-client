package com.example.alias_client.net

import com.example.alias_client.data.Room
import io.reactivex.Observable
import okhttp3.ResponseBody

interface RequestsI {

    fun createRoom():Observable<Room>
    fun addUser(roomid: Int):Observable<Int>
    fun deleteUser(roomid: Int, userid: Int):Observable<ResponseBody>
    fun activeUser(roomid: Int, userid: Int):Observable<Room>
    fun getRoomState(roomid: Int):Observable<Room>
    fun getWord():Observable<String>
    fun nextUser(roomid: Int, userid: Int):Observable<ResponseBody>
    fun update(roomid: Int, userid: Int, score: Int):Observable<ResponseBody>

}