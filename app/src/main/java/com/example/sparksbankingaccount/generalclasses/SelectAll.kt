package com.example.sparksbankingaccount.generalclasses

import android.content.Context
import android.widget.Toast
import com.example.sparksbankingaccount.database.DBHelper
import com.example.sparksbankingaccount.database.HistoryDBHelper
import com.example.sparksbankingaccount.models.HistoryBojo
import com.example.sparksbankingaccount.models.UserInDetails

class SelectAll(var context: Context) {

    fun selectAllUsers(): ArrayList<UserInDetails> {
        var arrayList = ArrayList<UserInDetails>()

        val db = DBHelper(context, null)
        val cursor = db.getallUsers()
        if (cursor!!.count == 0) {

            Toast.makeText(context, " not data  found", Toast.LENGTH_LONG).show()

        } else {
            while (cursor.moveToNext()) {
                arrayList.add(UserInDetails(cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getInt(4)))
            }
        }
        return arrayList

    }

    fun selectAllHistory(): ArrayList<HistoryBojo> {
        val db = HistoryDBHelper(context, null)
        val cursor = db.getallUsers()
        var myarrayList = ArrayList<HistoryBojo>()

        if (cursor!!.count == 0) {

            Toast.makeText(context, " not data  found", Toast.LENGTH_LONG).show()

        } else {
            while (cursor.moveToNext()) {
                myarrayList.add(HistoryBojo(cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getInt(5)))
            }
        }
        return myarrayList

    }

}