package com.example.alias_client

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import butterknife.ButterKnife
import butterknife.OnClick
import com.example.alias_client.MainActivity.Companion.room
import com.example.alias_client.MainActivity.Companion.user
import com.example.alias_client.MainActivity.Companion.winnerid
import com.example.alias_client.MainActivity.Companion.word
import com.example.alias_client.data.Room
import com.example.alias_client.data.User
import com.example.alias_client.net.Converter
import kotlinx.android.synthetic.main.activity_room.*


class RoomActivity : AppCompatActivity() {

    private val c = Converter()

    private val handler = Handler()


    private val timer = object: CountDownTimer(60000, 1000) {
        override fun onTick(ms: Long) {
            tvTimer.text = (ms/1000).toString()
        }
        override fun onFinish() {
            tvTimer.text = ""
            tvWord.text = ""
            btNo.visibility = View.INVISIBLE
            btYes.visibility = View.INVISIBLE
            btStart.visibility = View.VISIBLE
            c.update {
                c.nextUser {
                    c.updateRoomState(room.roomid){}
                }
            }

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room)
        ButterKnife.bind(this)
        c.update {
            toCheckRoomState()
        }

        tvRoom.text = room.roomid.toString()
        tvInfo.text = "Отправьте ID комнаты друзьям\nИгрок ${user.username}; Счет: ${user.score}"
    }

    private fun toCheckRoomState() {
        val runnable = Runnable {
            c.update {
                c.updateRoomState(room.roomid) {}
            }

            toCheckRoomState()
        }
        handler.postDelayed(runnable, 2000)
    }

    @OnClick(R.id.btStart)
    fun clickBtStart(){
        if (room.activeUserID == user.userid){
            c.getWord {
                tvWord.text = word
                timer.start()
                btYes.visibility = View.VISIBLE
                btNo.visibility = View.VISIBLE
                btStart.visibility = View.INVISIBLE
            }


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

        c.update {
            tvInfo.text = "Отправьте ID комнаты друзьям\nИгрок ${user.username}; Счет: ${user.score}"
        }

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
        c.update {
            tvInfo.text = "Отправьте ID комнаты друзьям\nИгрок ${user.username}; Счет: ${user.score}"
        }
    }

    @OnClick(R.id.btEnd)
    fun clickBtEnd(){
        if (user.userid == room.activeUserID) {
            c.getWinner {
                val winner = room.users.find { it.userid == winnerid }!!
                if (winner.score != 0) {
                    timer.cancel()
                    timer.onFinish()
                    tvWord.text = "${winner.username} победил\nсчет: ${winner.score}"
                } else {
                    showMessage("Начните игру")
                }

                c.updateRoomState(room.roomid) {tvInfo.text = "Отправьте ID комнаты друзьям\nИгрок ${user.username}; Счет: ${user.score}"}
            }
        } else
            showMessage("Вы не можете закончить игру")
    }

    private var back_pressed: Long = 0

    override fun onBackPressed() {
        if (back_pressed + 2000 > System.currentTimeMillis()){
            super.onBackPressed()
            c.deleteUser {  }
        }
        else Toast.makeText(this, "Нажмите еще раз для выхода", Toast.LENGTH_SHORT).show()
        back_pressed = System.currentTimeMillis()
    }

    private fun showMessage(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }
}
