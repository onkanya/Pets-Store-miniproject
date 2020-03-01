package com.example.petsstore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.etvAge
import kotlinx.android.synthetic.main.activity_main.etvName
import kotlinx.android.synthetic.main.activity_main.etvSex
import kotlinx.android.synthetic.main.activity_main.etvType
import kotlinx.android.synthetic.main.activity_main.etvWeight
import kotlinx.android.synthetic.main.activity_update_pets.*

class update_pets : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_pets)

        var pet_id = intent.getStringExtra("id")
//        Toast.makeText(applicationContext, "id = $pet_id", Toast.LENGTH_SHORT).show()
        var db = DatabaseHandler(this)
        var readData = db.listUpdate(pet_id)

        if(readData.count != 0) {
            readData.moveToFirst()
            etvName.setText(readData.getString(1))
            etvAge.setText(readData.getString(2))
            etvType.setText(readData.getString(3))
            etvSex.setText(readData.getString(4))
            etvWeight.setText(readData.getString(5))
        }

        btn_update.setOnClickListener() {
            val update = db.updateData(etvName.text.toString(),
                etvAge.text.toString().toInt(),
                etvType.text.toString(),
                etvSex.text.toString(),
                etvWeight.text.toString().toFloat(),
                pet_id)
            var i = Intent (this, MainActivity::class.java)
            startActivity(i)
        }

        btn_delete.setOnClickListener() {
            var builder = AlertDialog.Builder(this)
            builder.setTitle("Are you sure to delete pet's ${etvName.text.toString()}?")

            builder.setPositiveButton("Yes") {_,_ ->
                var delete = db.deleteData(pet_id)
                var i = Intent (this, MainActivity::class.java)
                startActivity(i)
            }
            builder.setNegativeButton("No") {_,_ ->

            }

            var dialog: AlertDialog = builder.create()
            dialog.show()
        }
    }
}
