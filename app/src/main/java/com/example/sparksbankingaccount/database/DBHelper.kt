package com.example.sparksbankingaccount.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.sparksbankingaccount.models.UserInDetails

class DBHelper(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {


    override fun onCreate(db: SQLiteDatabase?) {

        db!!.execSQL("create table " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT,email TEXT ,accountno TEXT,balance INTEGER )")

    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {

//        db!!.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);

        onCreate(db)

    }

    fun addUser(name: String, email: String, accountNo: String, balance: Int) {

        val values = ContentValues()
        values.put(COL_1, name)
        values.put(COL_2, email)
        values.put(COL_3, accountNo)
        values.put(COL_4, balance)

        val db = this.writableDatabase
        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun getallUsers(): Cursor? {

        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null)

    }

    fun updateEmployee(balance: Int, name: String): Int {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COL_4, balance) // EmpModelClass Name


        // Updating Row
        val success = db.update(TABLE_NAME, contentValues, "name=?", arrayOf(name))
        //2nd argument is String containing nullColumnHack
        db.close() // Closing database connection
        return success
    }


    fun getSpecificUser(id: Int): ArrayList<UserInDetails> {
        var db: SQLiteDatabase = readableDatabase
        var arrayList = ArrayList<UserInDetails>()
        var cursor: Cursor = db.rawQuery("select * from " + TABLE_NAME + " WHERE id =" + id, null);
        cursor.moveToFirst();
        while (cursor.isAfterLast == false) {
            arrayList.add(UserInDetails(cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getInt(4)))
            cursor.moveToNext();
        }
        return arrayList;
    }

    fun getSpecificUserByName(name: String): ArrayList<UserInDetails> {
        var db: SQLiteDatabase = readableDatabase
        var arrayList = ArrayList<UserInDetails>()
        var cursor: Cursor =
            db.rawQuery("select * from " + TABLE_NAME + " WHERE  name=?", arrayOf(name));
        cursor.moveToFirst();
        while (cursor.isAfterLast == false) {
            arrayList.add(UserInDetails(cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getInt(4)))
            cursor.moveToNext();
        }
        return arrayList;
    }

    //SELECT recordId FROM "testTable" WHERE ("testColumn" != "testValue") ORDER BY recordId ASC;


    fun selectNot(name: String): ArrayList<UserInDetails> {
        var db: SQLiteDatabase = readableDatabase
        var arrayList = ArrayList<UserInDetails>()
        var cursor: Cursor =
            db.rawQuery("select * from " + TABLE_NAME + " WHERE  (name!= ?)", arrayOf(name));
        cursor.moveToFirst();
        while (cursor.isAfterLast == false) {
            arrayList.add(UserInDetails(cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getInt(4)))
            cursor.moveToNext();
        }
        return arrayList;


    }

    companion object {
        // here we have defined variables for our database

        // below is variable for database name
        private val DATABASE_NAME = "clients.db"

        // below is the variable for database version
        private val DATABASE_VERSION = 1

        // below is the variable for table name
        val TABLE_NAME = "clients_table"

        // below is the variable for id column
        val COL_1 = "name"

        // below is the variable for name column
        val COL_2 = "email"

        // below is the variable for age column
        val COL_3 = "Accountno"
        val COL_4 = "balance"

    }

}
