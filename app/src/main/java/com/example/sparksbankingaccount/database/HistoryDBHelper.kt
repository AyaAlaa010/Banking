package com.example.sparksbankingaccount.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class HistoryDBHelper(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {


    override fun onCreate(db: SQLiteDatabase?) {

        db!!.execSQL("create table " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,sender TEXT,senderNo TEXT ,reciever TEXT,recieverNo TEXT,amount INTEGER )")

    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {

        db!!.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        onCreate(db)

    }

    fun addUser(sender: String, senderNo: String, reciever: String, recieverNo: String, amount: Int, ) {

        val values = ContentValues()
        values.put(COL_1, sender)
        values.put(COL_2, senderNo)
        values.put(COL_3, reciever)
        values.put(COL_4, recieverNo)
        values.put(COL_5, amount)

        val db = this.writableDatabase

        // all values are inserted into database
        db.insert(TABLE_NAME, null, values)

        // at last we are
        // closing our database
        db.close()
    }

    fun getallUsers(): Cursor? {

        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null)

    }


    companion object {
        // here we have defined variables for our database

        // below is variable for database name
        private val DATABASE_NAME = "history.db"

        // below is the variable for database version
        private val DATABASE_VERSION = 2

        // below is the variable for table name
        val TABLE_NAME = "History_table"

        // below is the variable for id column
        val COL_1 = "sender"

        // below is the variable for name column
        val COL_2 = "senderNo"

        // below is the variable for age column
        val COL_3 = "reciever"
        val COL_4 = "recieverNo"
        val COL_5 = "amount"


    }
}