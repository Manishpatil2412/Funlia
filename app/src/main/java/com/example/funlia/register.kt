package com.example.funlia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.funlia.R
import com.example.funlia.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class register : AppCompatActivity() {
    private lateinit var bname: EditText
    private lateinit var bemail: EditText
    private lateinit var bpassword: EditText
    private lateinit var bphone: EditText
    private lateinit var baddress: EditText
    private lateinit var bbirth:EditText
    private lateinit var bpassword1:EditText
    private lateinit var bsignup: Button
    private lateinit var mauth: FirebaseAuth




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        supportActionBar?.hide()
        bname=findViewById(R.id.name)
        bemail=findViewById(R.id.email)
        bpassword=findViewById(R.id.password)
        bphone=findViewById(R.id.phone)
        baddress=findViewById(R.id.address)
        bbirth=findViewById(R.id.birth)
        bpassword1=findViewById(R.id.password1)
        bsignup=findViewById(R.id.signup)

        mauth= FirebaseAuth.getInstance()


        bsignup.setOnClickListener {
            val name=bname.text.toString()
            val email=bemail.text.toString()
            val password=bpassword.text.toString()
            val phone=bphone.text.toString()
            val address=baddress.text.toString()
            val birth=bbirth.text.toString()
            val password1=bpassword1.text.toString()

            sign(name,email,password,phone,address,birth,password1)
        }

    }
    private fun sign(name:String,email:String,password:String,phone:String,address:String,birth:String,password1:String){
        mauth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    addUserToDataBase(name,email,mauth.currentUser?.uid!!,phone,address,birth,password1)
                    val int=Intent(this@register,home::class.java)
                    startActivity(int)
                    finish()

                }
                else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(this,"some error occured",Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun addUserToDataBase(name: String,email: String, uid: String,phone:String,address:String,birth:String,password1:String) {
        //bath=FirebaseDatabase.getInstance().getReference()
        //bath.child("user").child(uid).setValue(User(name,email,uid))
        val database = Firebase.database
        val myRef = database.getReference("user").child(uid)
        myRef.setValue(User(name,email,uid,phone,address,birth,password1))
    }
}