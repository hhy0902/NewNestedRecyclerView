package com.example.nestedrecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nestedrecyclerview.databinding.ActivityMainBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    var roomList = mutableListOf<Room>()
    var deviceDataList = mutableListOf<DeviceData>()

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    var adapter : RoomAdapter = RoomAdapter(this,
        roomList = roomList,
        deviceDataList = deviceDataList)

    var i = 2
    var deviceText = ""
    var sb = StringBuilder()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        roomList.add(Room("Room 1"))

        findViewById<Button>(R.id.btn_save).setOnClickListener {

            deviceDataList.forEach {
                sb.append(it.deviceName).append(it.deviceValue)
                Log.d("qwer","${it.deviceName}")

            }
            deviceText = sb.toString()
            Log.d("qwer","${deviceText}")
            findViewById<TextView>(R.id.gateWay).text = deviceText
            sb = StringBuilder()

        }




        findViewById<RecyclerView>(R.id.recyclerView).adapter = adapter
        findViewById<RecyclerView>(R.id.recyclerView).layoutManager = LinearLayoutManager(this)

        findViewById<FloatingActionButton>(R.id.button).setOnClickListener {
            i = roomList.size + 1
            adapter.roomList.add(Room("Room $i"))
            adapter.notifyDataSetChanged()
        }

    }

}






























