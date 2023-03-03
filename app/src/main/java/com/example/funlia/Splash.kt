package com.example.funlia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.google.firebase.auth.FirebaseAuth

class Splash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val auth:FirebaseAuth=FirebaseAuth.getInstance()
        supportActionBar?.hide()
        val handler= Handler(Looper.getMainLooper())
        handler.postDelayed({
            if(auth.currentUser!=null){
                val intent= Intent(this, home::class.java)
                startActivity(intent)
                finish()

            }else{
                val intent=Intent(this,login::class.java)
                startActivity(intent)
            }
                            },3000)

    }
}