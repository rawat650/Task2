package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.w3c.dom.Text

class FourthActivity:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fourth_activity)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setTitle("TODO APP")
        val tvTwo = findViewById<TextView>(R.id.tvTwo)
        val tvOne = findViewById<TextView>(R.id.tvOne)
       tvOne.text = intent.getStringExtra("main")
        tvTwo.text = intent.getStringExtra("date")

        }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    fun back(view: android.view.View) {
        onBackPressed()

    }

}
