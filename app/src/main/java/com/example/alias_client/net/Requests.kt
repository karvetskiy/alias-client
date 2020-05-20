package com.example.alias_client.net

import dev.karvetskiy.Room
import dev.karvetskiy.User
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface Requests {

    @GET("createRoom")
    fun createRoom():Observable<Room>

    @GET("destroyRoom")
    fun destroyRoom(@Query("roomid") roomId: Int):Observable<String>

    @GET("addUser")
    fun addUser(@Query("roomid")roomid: Int):Observable<Room>

    @POST("deleteUser")
    fun deleteUser(@Query("roomid")roomId: Int, @Query("userid")userid: Int):Observable<ResponseBody>

    @POST("activeUser")
    fun activeUser(@Query("roomid")roomId: Int, @Query("userid")userid: Int):Observable<ResponseBody>

    @GET("instance")
    fun instance(@Query("roomid") roomid: Int):Observable<Room>

}