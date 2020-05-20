package com.example.alias_client.net

import dev.karvetskiy.Room
import io.reactivex.Observable
import okhttp3.ResponseBody

interface RequestsI {

    fun getNewRoom():Observable<Room>
}