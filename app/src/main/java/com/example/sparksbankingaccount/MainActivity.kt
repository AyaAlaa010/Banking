package com.example.sparksbankingaccount

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import com.example.sparksbankingaccount.database.DBHelper
import com.example.sparksbankingaccount.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var sharedPreferences: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        sharedPreferences = this.getSharedPreferences("delete table", 0)

        val value = sharedPreferences!!.getBoolean("insert", false)
        Log.i("before TAG", "onCreate: " + value)


val db = DBHelper(this, null)

        if (value == false) {
            db.addUser("aya", "ayaalaa@gmail.com", "234567ac", 5000)
            db.addUser("amira", "amira@gmail.com", "784329hb", 10000)
            db.addUser("ahmed", "ahmed@gmail.com", "125689rf", 30000)
            db.addUser("tuqa", "tuqa@gmail.com", "567843ed", 8000)
            db.addUser("shimaa", "shimaa@gmail.com", "834562sw", 12000)
            db.addUser("sara", "sara@gmail.com", "128962sw", 9000)
            db.addUser("khloud", "khloud@gmail.com", "673902cv", 8735)
            db.addUser("shahenda", "ahahi@gmail.com", "998762hj", 18000)
            val editor: SharedPreferences.Editor = sharedPreferences!!.edit()
            editor.putBoolean("insert", true)
            editor.apply()
            editor.commit()
            Log.i(" after TAG", "onCreate: " + value)
            // Toast to message on the screen
            Toast.makeText(this, " added to database", Toast.LENGTH_LONG).show()
        }

    }
}