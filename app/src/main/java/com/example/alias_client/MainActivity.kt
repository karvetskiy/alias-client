package com.example.alias_client

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import butterknife.ButterKnife
import butterknife.OnClick
import com.example.alias_client.net.RequestsI
import com.example.alias_client.net.RequestsImpl
import com.example.alias_client.data.Room
import com.example.alias_client.data.User
import com.example.alias_client.net.Converter
import io.reactivex.rxkotlin.addTo
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.HttpException
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private val c = Converter()

    companion object {
        var room = Room()
        var user = User()
        var word = ""
        var winnerid = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)
    }


    @OnClick(R.id.btConnect)
    fun clickBtConnect(){
        if (etRoomNumber.visibility == View.GONE){
            etRoomNumber.visibility = View.VISIBLE
            showMessage("Введите номер комнаты")
        }else {
            if (etName.text.toString() != "") {
                if (etRoomNumber.text.toString() == "") {
                    showMessage("Введите номер комнаты")
                } else
                    c.addUser(etRoomNumber.text.toString().toInt()) {
                        c.updateRoomState(etRoomNumber.text.toString().toInt()) {
                            user.username = etName.text.toString()
                            c.update {
                                val intent = Intent(this, RoomActivity::class.java)
                                startActivity(intent)
                            }

                        }
                    }
            } else
                showMessage("Введите имя")
        }
    }


    @OnClick(R.id.btCreateRoom)
    fun clickBtCreateRoom(){
        if (etName.text.toString() != ""){
            c.createRoom {
                c.addUser(room.roomid){
                    c.activeUser {
                        user.username = etName.text.toString()
                        c.update {
                            val intent = Intent(this, RoomActivity::class.java)
                            startActivity(intent)
                        }
                    }
                }
            }
        } else
            showMessage("Введите имя")
    }

    private fun showMessage(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

}
