package com.example.alias_client.data

class RequestBody {
    var roomid = 0
    var userid = 0
    var score = 0
    var username = ""

    constructor(roomid: Int){
        this.roomid = roomid
    }

    constructor(roomid: Int, userid: Int){
        this.roomid = roomid
        this.userid = userid
    }

    constructor(roomid: Int, userid: Int, score: Int, username: String){
        this.roomid = roomid
        this.userid = userid
        this.score = score
        this.username = username
    }
}