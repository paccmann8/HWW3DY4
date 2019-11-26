package com.example.hww3dy4

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class RoomAdaptor(private val roomList: List<String>, private val delegate: RoomAdaptorDelegate) : BaseAdapter() {


    interface RoomAdaptorDelegate{
        fun getImage(guestPosition: Int)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view =
            LayoutInflater.from(parent?.context)
                .inflate(R.layout.room_item_layout, parent, false)

        view.findViewById<TextView>(R.id.room_text).text =
            roomList[position]

        view.setOnClickListener { _ ->
            delegate.getImage(position)
        }

        return view

    }

    override fun getItem(position: Int): Any {
        return roomList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return roomList.size
    }
}