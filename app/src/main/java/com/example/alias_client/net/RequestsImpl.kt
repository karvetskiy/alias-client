package com.example.alias_client.net

import com.example.alias_client.data.Room
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody

class RequestsImpl(): BaseRequest(), RequestsI {


    override fun createRoom(): Observable<Room> =
        getBnc()
            .createRoom()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())

    override fun destroyRoom(roomid: Int): Observable<ResponseBody> =
        getBnc()
            .destroyRoom(roomid)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())


    override fun addUser(roomid: Int): Observable<Int> =
        getBnc()
            .addUser(roomid)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())


    override fun deleteUser(roomid: Int, userid: Int): Observable<ResponseBody> =
        getBnc()
            .deleteUser(roomid, userid)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())


    override fun activeUser(roomid: Int, userid: Int): Observable<Room> =
        getBnc()
            .activeUser(roomid, userid)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())

    override fun getRoomState(roomid: Int): Observable<Room> =
        getBnc()
            .getRoomState(roomid)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())



    override fun getWord(): Observable<String> =
        getBnc()
            .getWord()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())

}