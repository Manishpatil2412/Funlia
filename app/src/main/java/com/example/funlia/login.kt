package com.example.funlia

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.core.content.ContextCompat.startActivity
import com.example.funlia.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class login : AppCompatActivity() {
    private lateinit var bemail: EditText
    private lateinit var bpassword: EditText
    private lateinit var blogin:Button
    private lateinit var bsign:Button
    // private lateinit var bsignup: Button
    //  private lateinit var bforgot: TextView
    private lateinit var bauth: FirebaseAuth

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        supportActionBar?.hide()

        bemail=findViewById(R.id.email)
        bpassword=findViewById(R.id.password)
        blogin=findViewById(R.id.lo)
        bsign=findViewById(R.id.sign)
        // bsignup=findViewById(R.id.signup)
        // bforgot=findViewById(R.id.forgot)

        bauth= FirebaseAuth.getInstance()

        /*  bsignup.setOnClickListener {
              val intent = Intent(this,signup::class.java)
              startActivity(intent)
          }*/

        bsign.setOnClickListener {
            val intent=Intent(this,register::class.java)
            startActivity(intent)
        }
        blogin.setOnClickListener {
            val email=bemail.text.toString()
            val password=bpassword.text.toString()

            logn(email,password)
        }

        /* bforgot.setOnClickListener {
             val intent = Intent(this,Contacts::class.java)
             startActivity(intent)
         }*/
    }

    private fun logn(email:String,password:String){
        bauth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val intent= Intent(this@login,home::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(this,"User does not exist", Toast.LENGTH_SHORT).show()
                }
            }


    }
}



