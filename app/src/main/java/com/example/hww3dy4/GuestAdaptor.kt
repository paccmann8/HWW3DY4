package com.example.hww3dy4

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class GuestAdapter(private val guestList: List<Guest>) :
    RecyclerView.Adapter<GuestAdapter.MyCustomViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyCustomViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.guest_item_layout, parent, false)
        return MyCustomViewHolder(view)
    }

    override fun getItemCount(): Int {
        return guestList.size
    }

    override fun onBindViewHolder(holder: MyCustomViewHolder, position: Int) {
        holder.apply {
            roomNumberText.text = guestList[position].roomNumber.toString()
            guestNameTextView.text = guestList[position].name
            priceTextView.text = guestList[position].price?.toDollars()
        }
    }

    private fun Int.toDollars(): String {
        return "$ $this.00"
    }


    inner class MyCustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val roomNumberText = itemView.findViewById<TextView>(R.id.room_number_tetview)
        val guestNameTextView = itemView.findViewById<TextView>(R.id.guest_name_textview)
        val priceTextView = itemView.findViewById<TextView>(R.id.price_textview)
    }
}