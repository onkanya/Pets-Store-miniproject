package com.example.petsstore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showPetInfo()
        btn_add.setOnClickListener() {
            if(etvName.text.toString().length > 0 && etvAge.text.toString().length > 0 &&
                etvType.text.toString().length > 0 && etvSex.text.toString().length > 0 && etvWeight.text.toString().length > 0) {
                var pet = Pet(etvName.text.toString(), etvAge.text.toString().toInt(), etvType.text.toString(), etvSex.text.toString(), etvWeight.text.toString().toFloat())
                var db = DatabaseHandler(this)
                db.insertData(pet)
            } else {
                Toast.makeText(applicationContext, "กรุณากรอกข้อมูลให้ครบถ้วน", Toast.LENGTH_SHORT).show()
            }
            showPetInfo()
        }
    }

    fun showPetInfo() {
        var db = DatabaseHandler(this)
        var readData = db.listData()
        var listItem = mutableListOf<String>()
        var petId = mutableListOf<String>()

        if(readData.count != 0) {
            while (readData.moveToNext()) {
                listItem.add("Id: " + readData.getString(0)
                        + " Name: "+ readData.getString(1) + "\n"
                        + "Age: " + readData.getString(2)
                        + " Type: " + readData.getString(3) + "\n"
                        + "Sex: " + readData.getString(4)
                        + " Weight: " + readData.getString(5)
                )
                petId.add(readData.getString(0))
            }
        }

        var lv = findViewById<ListView>(R.id.lv_pets)
        var setAdapter = ArrayAdapter<String>(this, R.layout.listview_pets, listItem)
        lv.adapter = setAdapter

        lv.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
//            Toast.makeText(applicationContext, "You selected : " + petId[position], Toast.LENGTH_SHORT).show()
            var i = Intent(this, update_pets::class.java)
            i.putExtra("id", petId[position])
            startActivity(i)
        }
    }
}
