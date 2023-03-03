package com.example.funlia

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*


class Chat : AppCompatActivity() {

     private lateinit var userRecycleView:RecyclerView
     private lateinit var userList:ArrayList<User>
     private lateinit var adapter: UserAdapter
     private lateinit var mAuth: FirebaseAuth
     private lateinit var mDbRef:DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        mAuth=FirebaseAuth.getInstance()
        mDbRef=FirebaseDatabase.getInstance().reference
        mDbRef.child("user").addValueEventListener(object :ValueEventListener{
            @SuppressLint("NotifyDataSetChanged")
            override fun onDataChange(snapshot: DataSnapshot) {
                userList.clear()
                for (postSnapshot in snapshot.children) {
                    val currentuser = postSnapshot.getValue(User::class.java)
                    if(mAuth.currentUser?.uid !=currentuser?.uid) {
                        userList.add(currentuser!!)
                    }
                }
                adapter.notifyDataSetChanged()
            }


            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

        userList=ArrayList()
        adapter= UserAdapter(this,userList)
        userRecycleView=findViewById(R.id.userrecycleview)
        userRecycleView.layoutManager=LinearLayoutManager(this)
        userRecycleView.adapter=adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.logout) {
            mAuth.signOut()
            val intent= Intent(this@Chat, login::class.java)
            finish()
            startActivity(intent)
            return true
        }
    return true
    }
}