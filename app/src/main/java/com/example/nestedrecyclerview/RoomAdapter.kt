package com.example.nestedrecyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import java.lang.reflect.Member

class RoomAdapter(var activity: MainActivity,
    val roomList: MutableList<Room>,
    val deviceDataList: MutableList<DeviceData>) :
    RecyclerView.Adapter<RoomAdapter.RoomViewHolder> () {

    inner class RoomViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val roomTextView : TextView
        val btn_del : ImageButton
        val btn_add : ImageButton
        var deviceList = mutableListOf<Device>()
        val device_recyclerView : RecyclerView
        var i = 0
        var adapter : DeviceAdapter = DeviceAdapter(activity = activity,deviceList = deviceList,
            deviceDataList = deviceDataList)

        init {

            roomTextView = itemView.findViewById(R.id.roomText)
            btn_del = itemView.findViewById(R.id.btn_del)
            btn_add = itemView.findViewById(R.id.btn_add)
            device_recyclerView = itemView.findViewById(R.id.device_recyclerView)
            btn_del.setOnClickListener {
                val position : Int = adapterPosition
                val room = roomList.get(position)
                roomList.removeAt(position)
                notifyDataSetChanged()
            }

            device_recyclerView.adapter = adapter
            device_recyclerView.layoutManager = LinearLayoutManager(itemView.context)

            btn_add.setOnClickListener {
                i = deviceList.size + 1
                adapter.deviceList.add(Device("Device $i"))
                adapter.notifyDataSetChanged()
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomAdapter.RoomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.room_item,parent,false)
        return RoomViewHolder(view)
    }

    override fun onBindViewHolder(holder: RoomAdapter.RoomViewHolder, position: Int) {
        holder.roomTextView.text = roomList.get(position).name
    }

    override fun getItemCount(): Int {
        return roomList.size
    }

}
























