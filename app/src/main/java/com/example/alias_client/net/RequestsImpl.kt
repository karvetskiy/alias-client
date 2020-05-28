package com.example.alias_client.net

import com.example.alias_client.data.RequestBody
import com.example.alias_client.data.Room
import com.example.alias_client.data.User
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


    override fun addUser(roomid: Int): Observable<Int> =
        getBnc()
            .addUser(roomid)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())


    override fun deleteUser(body: RequestBody): Observable<ResponseBody> =
        getBnc()
            .deleteUser(body)
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

    override fun nextUser(body: RequestBody): Observable<ResponseBody> =
        getBnc()
            .nextUser(body)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())

    override fun update(body: RequestBody): Observable<ResponseBody> =
        getBnc()
            .update(body)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())

    override fun winner(roomid: Int): Observable<Int> =
        getBnc()
            .winner(roomid)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())

    override fun start(body: RequestBody): Observable<ResponseBody> =
        getBnc()
            .start(body)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())

    override fun end(body: RequestBody): Observable<ResponseBody> =
        getBnc()
            .end(body)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())


}