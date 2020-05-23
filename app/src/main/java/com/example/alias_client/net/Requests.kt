package com.example.alias_client.net

import com.example.alias_client.data.Room
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

interface Requests {

    @GET("createRoom")
    fun createRoom():Observable<Room>

    @GET("addUser")
    fun addUser(@Query("roomid")roomid: Int):Observable<Int>

    @GET("deleteUser")
    fun deleteUser(@Query("roomid")roomid: Int, @Query("userid")userid: Int):Observable<ResponseBody>

    @GET("activeUser")
    fun activeUser(@Query("roomid")roomid: Int, @Query("userid")userid: Int):Observable<Room>

    @GET("getRoomState")
    fun getRoomState(@Query("roomid") roomid: Int):Observable<Room>

    @GET("getWord")
    fun getWord():Observable<String>

    @GET("nextUser")
    fun nextUser(@Query("roomid")roomid: Int, @Query("userid")userid: Int):Observable<ResponseBody>

    @GET("updateOnServer")
    fun update(@Query("roomid")roomid: Int, @Query("userid")userid: Int, @Query("score")score: Int):Observable<ResponseBody>

}