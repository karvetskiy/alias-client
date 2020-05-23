package com.example.alias_client

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Toast
import butterknife.ButterKnife
import butterknife.OnClick
import com.example.alias_client.MainActivity.Companion.room
import com.example.alias_client.MainActivity.Companion.user
import com.example.alias_client.MainActivity.Companion.word
import com.example.alias_client.data.Room
import com.example.alias_client.data.User
import com.example.alias_client.net.Converter
import com.example.alias_client.net.RequestsI
import com.example.alias_client.net.RequestsImpl
import kotlinx.android.synthetic.main.activity_room.*
import kotlin.time.measureTimedValue

class RoomActivity : AppCompatActivity() {

    private val c = Converter()

    private val handler = Handler()


    val timer = object: CountDownTimer(60000, 1000) {
        override fun onTick(ms: Long) {
            tvTimer.text = (ms/1000).toString()
        }
        override fun onFinish() {
            tvTimer.text = ""
            tvWord.text = "Time's up"
            btNo.visibility = View.INVISIBLE
            btYes.visibility = View.INVISIBLE
            c.update {
                c.nextUser {
                    c.updateRoomState(room.roomid){}
            }}

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room)
        ButterKnife.bind(this)
        tvRoom.text = room.roomid.toString()
        tvInfo.text = "Send room ID to your friends\nUser #${user.userid}"
        toCheckRoomState()
    }

    private fun toCheckRoomState() {
        val runnable = Runnable {
            c.updateRoomState(room.roomid) {}
            toCheckRoomState()
        }
        handler.postDelayed(runnable, 10000)
    }

    @OnClick(R.id.btStart)
    fun clickBtStart(){
        if (room.activeUserID == user.userid){
            c.getWord {
                Log.i("word", word)
                tvWord.text = word
                timer.start()
                btYes.visibility = View.VISIBLE
                btNo.visibility = View.VISIBLE
            }
            Log.i("word", word)


        } else{
            showMessage("Сейчас не ваш ход")
        }
    }

    @OnClick(R.id.btLeaveRoom)
    fun clickBtLeaveRoom(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        c.deleteUser {
            room = Room()
            user = User()
        }


    }

    @OnClick(R.id.btYes)
    fun clickBtYes(){
        c.getWord {
            tvWord.text = word
        }
        user.score += 1
    }

    @OnClick(R.id.btNo)
    fun clickBtNo(){
        c.getWord {
            tvWord.text = word
        }
        user.score -= 2
        if (user.score < 0){
            user.score = 0
        }
    }

    private fun showMessage(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }
}
