package com.example.alias_client

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import com.example.alias_client.MainActivity.Companion.room
import com.example.alias_client.data.User
import com.example.alias_client.net.RequestsI
import com.example.alias_client.net.RequestsImpl
import kotlinx.android.synthetic.main.activity_room.*

class RoomActivity : AppCompatActivity() {

    private val requests: RequestsI = RequestsImpl()

    private val handler = Handler()

    private val user = User()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room)
        tvRoom.text = room.roomid.toString()
        requests.activeUser(room.roomid, user.userid)
        tvInfo.text = "User #${user.userid}"
    }

    private fun toCheckRoomState() {
        val runnable = Runnable {
            requests.getRoomState(MainActivity.room.roomid)
            toCheckRoomState()
        }
        handler.postDelayed(runnable, 10000)
    }



    private fun showMessage(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }
}
