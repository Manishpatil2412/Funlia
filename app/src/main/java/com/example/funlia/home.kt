package com.example.funlia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class home : AppCompatActivity() {
    private lateinit var chaaaa:ImageView
    private lateinit var son:ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        chaaaa=findViewById(R.id.whatsapp)
        chaaaa.setOnClickListener {
            val intent=Intent(this@home,Chat::class.java)
            startActivity(intent)
        }

    }
}