package com.example.petsstore

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

val DATABASE_NAME = "PetsStoreDB"
val TABLE_NAME = "PetsInfo"
val COL_NAME = "name"
val COL_AGE = "age"
val COL_TYPE = "type"
val COL_SEX = "sex"
val COL_WEIGHT = "weight"
val COL_ID = "id"

class DatabaseHandler (var context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        var createTable = "CREATE TABLE " + TABLE_NAME + "(" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_NAME + " VARCHAR(256)," +
                COL_AGE + " INTEGER," +
                COL_TYPE + " VARCHAR(256)," +
                COL_SEX + " VARCHAR(256)," +
                COL_WEIGHT + " FLOAT(2)" + ")"
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }

    fun insertData (pet: Pet) {
        var dbs = this.writableDatabase
        var cv = ContentValues()
        cv.put(COL_NAME, pet.name)
        cv.put(COL_AGE, pet.age)
        cv.put(COL_TYPE, pet.type)
        cv.put(COL_SEX, pet.sex)
        cv.put(COL_WEIGHT, pet.weight)

        var result = dbs.insert(TABLE_NAME, null, cv)

        if(result == -1.toLong()) {
            Toast.makeText(context, "Insert Fail", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Insert Success", Toast.LENGTH_SHORT).show()
        }
    }

    fun listData (): Cursor {
        var dbs = this.readableDatabase
        var query = "SELECT * FROM " + TABLE_NAME
        var result = dbs.rawQuery(query, null)
        return  result
    }

    fun listUpdate(pet_id: String): Cursor {
        var dbs = this.readableDatabase
        var query = "SELECT * FROM " + TABLE_NAME + " WHERE id = " + pet_id
        var result = dbs.rawQuery(query, null)
        return result
    }

    fun updateData(name: String, age: Int, type: String, sex: String, weight: Float, pet_id: String) {
        val values = ContentValues()
        values.put(COL_NAME, name)
        values.put(COL_AGE, age)
        values.put(COL_TYPE, type)
        values.put(COL_SEX, sex)
        values.put(COL_WEIGHT, weight)

        var dbs = writableDatabase
        var result = dbs.update(TABLE_NAME, values, "id = $pet_id", arrayOf())

        print("result" + result)

        if(result != 1) {
            Toast.makeText(context, "Update Fail", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Update Success", Toast.LENGTH_SHORT).show()
        }
    }

    fun deleteData(pet_id: String) {
        var dbs = writableDatabase
        var result = dbs.delete(TABLE_NAME, "id = $pet_id", arrayOf())

        if(result != 1) {
            Toast.makeText(context, "Delete Fail", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Delete Success", Toast.LENGTH_SHORT).show()
        }

    }

}
