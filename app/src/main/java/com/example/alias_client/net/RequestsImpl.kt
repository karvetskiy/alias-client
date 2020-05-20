package com.example.alias_client.net

import dev.karvetskiy.Room
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody

class RequestsImpl(): BaseRequest(), RequestsI {


    override fun getNewRoom(): Observable<Room> =
        getBnc()
            .createRoom()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
}