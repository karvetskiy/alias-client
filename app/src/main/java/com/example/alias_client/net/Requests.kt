package com.example.alias_client.net

import com.example.alias_client.data.RequestBody
import com.example.alias_client.data.Room
import com.example.alias_client.data.User
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface Requests {

    @GET("createRoom")
    fun createRoom():Observable<Room>

    @GET("addUser")
    fun addUser(@Query("roomid")roomid: Int):Observable<Int>

    @POST("deleteUser")
    fun deleteUser(@Body body: RequestBody):Observable<ResponseBody>

    @GET("activeUser")
    fun activeUser(@Query("roomid")roomid: Int, @Query("userid")userid: Int):Observable<Room>

    @GET("getRoomState")
    fun getRoomState(@Query("roomid") roomid: Int):Observable<Room>

    @GET("getWord")
    fun getWord():Observable<String>

    @POST("nextUser")
    fun nextUser(@Body body: RequestBody):Observable<ResponseBody>

    @POST("updateOnServer")
    fun update(@Body body: RequestBody):Observable<ResponseBody>

    @GET("winner")
    fun winner(@Query("roomid")roomid: Int):Observable<Int>

    @POST("start")
    fun start(@Body body: RequestBody):Observable<ResponseBody>

    @POST("end")
    fun end(@Body body: RequestBody):Observable<ResponseBody>

}