package com.example.hww3dy4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), RoomAdaptor.RoomAdaptorDelegate{
    override fun getImage(guestPosition: Int) {

    }


    private var roomList = mutableListOf<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        add_Guest.setOnClickListener {
            intent = Intent(this, add_Guest::class.java)
            startActivity(intent)
        }




        roomList.add("Pac Man themed room")
        roomList.add("James Bond themed room")
        roomList.add("Gundam themed room")


        displayUsers()

    }

    private fun displayUsers() {

        val myBaseAdapter = RoomAdaptor(roomList,this)
        room_list.adapter = myBaseAdapter
    }


    
}
