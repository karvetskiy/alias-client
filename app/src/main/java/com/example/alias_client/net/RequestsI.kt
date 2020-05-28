package com.example.alias_client.net

import com.example.alias_client.data.RequestBody
import com.example.alias_client.data.Room
import com.example.alias_client.data.User
import io.reactivex.Observable
import okhttp3.ResponseBody

interface RequestsI {

    fun createRoom():Observable<Room>
    fun addUser(roomid: Int):Observable<Int>
    fun deleteUser(body: RequestBody):Observable<ResponseBody>
    fun activeUser(roomid: Int, userid: Int):Observable<Room>
    fun getRoomState(roomid: Int):Observable<Room>
    fun getWord():Observable<String>
    fun nextUser(body: RequestBody):Observable<ResponseBody>
    fun update(body: RequestBody):Observable<ResponseBody>
    fun winner(roomid: Int):Observable<Int>
    fun start(body: RequestBody):Observable<ResponseBody>
    fun end(body: RequestBody):Observable<ResponseBody>

}