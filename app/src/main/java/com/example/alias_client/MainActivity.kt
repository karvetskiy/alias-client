package com.example.alias_client

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import butterknife.ButterKnife
import butterknife.OnClick
import com.example.alias_client.net.RequestsI
import com.example.alias_client.net.RequestsImpl
import com.example.alias_client.data.Room
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private val requests: RequestsI = RequestsImpl()

    private val handler = Handler()

    companion object {
        var room = Room()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)
    }


    @OnClick(R.id.btConnect)
    fun clickBtConnect(){
        if (etRoomNumber.text.toString() != ""){
            try {
                room = requests.addUser(etRoomNumber.text.toString().toInt()) as Room
            } catch (e: Exception) {
                showMessage("Server Error")
            }
            finally {
                val intent = Intent(this, RoomActivity::class.java)
                startActivity(intent)
            }
        } else
            showMessage("Введите номер комнаты")

    }

    @OnClick(R.id.btCreateRoom)
    fun clickBtCreateRoom(){
        try {
            room = requests.createRoom() as Room
        }catch (e: Exception){
            showMessage("Server Error")
        }
        finally {
            val intent = Intent(this, RoomActivity::class.java)
            startActivity(intent)
        }
    }

    private fun showMessage(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

}
