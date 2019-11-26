package com.example.hww3dy4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout.VERTICAL
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_add_guests.*

class AddGuests : AppCompatActivity() {
    private var guestList = mutableListOf<Guest>()
    private lateinit var myDatabaseHelper: MyDatabaseHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_guests)


        myDatabaseHelper = MyDatabaseHelper(this)

        check_in_button.setOnClickListener { _ ->

            saveToDatabase()
        }
    }

    override fun onResume() {
        super.onResume()
        readFromDatabase()
    }



    private fun clearFields() {
        name_edittext.text.clear()
        room_number_edit_text.text.clear()
        price_edit_text.text.clear()
    }

    private fun displayUsers() {

        val recyclerAdapter = GuestAdapter(guestList)
        hotel_list_view.adapter = recyclerAdapter
        val layoutMgr = LinearLayoutManager(this)
        hotel_list_view.layoutManager = layoutMgr

    }



    private fun saveToDatabase() {
        val guestName = name_edittext.text.toString()
        val guestRoom = room_number_edit_text.text.toString()
        val roomPrice = price_edit_text.text.toString()
        val newGuest = Guest(guestName, Integer.parseInt(guestRoom), Integer.parseInt(roomPrice))
        myDatabaseHelper.insertGuest(newGuest)
        clearFields()
        readFromDatabase()
    }

    private fun readFromDatabase() {
        guestList = mutableListOf()

        val cursor = myDatabaseHelper.readAllGuests()
        cursor.moveToFirst()

        if (cursor.count > 0) {
            val guest = Guest(
                cursor.getString(cursor.getColumnIndex(MyDatabaseHelper.COLUMN_NAME)),
                cursor.getInt(cursor.getColumnIndex(MyDatabaseHelper.COLUMN_ROOMNUMBER)),
                cursor.getInt(cursor.getColumnIndex(MyDatabaseHelper.COLUMN_PRICE))
            )
            guestList.add(guest)
        }
        while (cursor.moveToNext()) {
            val guestName = cursor.getString(cursor.getColumnIndex(MyDatabaseHelper.COLUMN_NAME))
            val guestRoom =
                cursor.getString(cursor.getColumnIndex(MyDatabaseHelper.COLUMN_ROOMNUMBER))
            val roomPrice = cursor.getInt(cursor.getColumnIndex(MyDatabaseHelper.COLUMN_PRICE))
            val readGuest = Guest(guestName, Integer.parseInt(guestRoom), roomPrice)
            guestList.add(readGuest)
        }
        displayUsers()

    }


}