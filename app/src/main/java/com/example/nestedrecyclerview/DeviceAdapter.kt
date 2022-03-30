package com.example.nestedrecyclerview

import android.content.Intent
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView

class DeviceAdapter(activity: MainActivity,val deviceList : MutableList<Device>,
    val deviceDataList: MutableList<DeviceData>) :
    RecyclerView.Adapter<DeviceAdapter.DeviceViewHolder> () {

    inner class DeviceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val deviceTextView : TextView
        val btn_del : ImageButton
        val spinner : Spinner
        val deviceEditText : EditText
        var deviceData = ""
        var deviceValue = ""
        val adapter = ArrayAdapter.createFromResource(itemView.context,R.array.planets_array,android.R.layout.simple_spinner_item)


        init {
            deviceTextView = itemView.findViewById(R.id.deviceText)
            deviceEditText = itemView.findViewById(R.id.deviceEditText)
            spinner = itemView.findViewById(R.id.spinner)
            btn_del = itemView.findViewById(R.id.btn_del)
            btn_del.setOnClickListener {
                val position : Int = adapterPosition
                //val room = roomList.get(position)
                deviceList.removeAt(position)
                notifyDataSetChanged()

            }

            spinner.adapter = adapter
            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    Toast.makeText(itemView.context,"${spinner.getItemAtPosition(p2)}",Toast.LENGTH_SHORT).show()
                    deviceData = spinner.getItemAtPosition(p2).toString()
                    //val intent = Intent(itemView.context,MainActivity::class.java)
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }

            }
            deviceEditText.setOnKeyListener { view, keycode, event ->
                if (keycode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_DOWN) {
                    Toast.makeText(itemView.context,"${deviceEditText.text}",Toast.LENGTH_SHORT).show()
                    deviceValue = deviceEditText.text.toString()

                    deviceDataList.add(DeviceData("$deviceData","$deviceValue"))
                    return@setOnKeyListener true
                }
                return@setOnKeyListener false
            }

        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int): DeviceAdapter.DeviceViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.device_item,parent,false)
        return DeviceViewHolder(view)
    }

    override fun onBindViewHolder(holder: DeviceAdapter.DeviceViewHolder, position: Int) {
        holder.deviceTextView.text = deviceList.get(position).name

    }

    override fun getItemCount(): Int {
        return deviceList.size
    }

}


















