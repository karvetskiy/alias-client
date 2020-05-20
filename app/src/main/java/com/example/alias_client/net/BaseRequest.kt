package com.example.alias_client.net

import com.example.alias_client.BuildConfig

open class BaseRequest () {

    private val baseNetConstructor = BaseNetConstructor()

    protected fun getBnc():Requests = baseNetConstructor.create(BuildConfig.BASEURL, Requests::class.java)

}