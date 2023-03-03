package com.example.funlia

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class chat2 : AppCompatActivity() {
    private lateinit var messagerecyclerview : RecyclerView
    private lateinit var messagebox : EditText
    private lateinit var sedbuttton : ImageView
    private lateinit var messageadapter: messageadapter
    private lateinit var messageList : ArrayList<message>
    private lateinit var bauth: DatabaseReference


    var reseiverroom:String?=null
    var senderroom:String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat3)


      //  val name=intent.getStringExtra("name")
       // val reseiveruid =intent.getStringExtra("uid")

       // val senderuid=FirebaseAuth.getInstance().currentUser?.uid

      //  bauth=FirebaseDatabase.getInstance().getReference()

      //  senderroom = reseiveruid + senderuid
       // reseiverroom=senderuid +reseiveruid


        val name=intent.getStringExtra("name")
        val reseiveruid=intent.getStringExtra("uid")

        val senderuid=FirebaseAuth.getInstance().currentUser?.uid
        bauth=FirebaseDatabase.getInstance().getReference()


        senderroom = reseiveruid + senderuid
        reseiverroom=senderuid +reseiveruid
        supportActionBar?.title = name

        messagerecyclerview=findViewById(R.id.charRecycleview)
        messagebox=findViewById(R.id.messageBox)
        sedbuttton=findViewById(R.id.send5)
        messageList= ArrayList()
        messageadapter= messageadapter(this,messageList)

        messagerecyclerview.layoutManager= LinearLayoutManager(this)
        messagerecyclerview.adapter =messageadapter



        bauth.child("chats").child(senderroom!!).child("messages")
            .addValueEventListener(object : ValueEventListener{
                @SuppressLint("NotifyDataSetChanged")
                override fun onDataChange(snapshot: DataSnapshot) {
                    messageList.clear()
                    for (postSnapshot in snapshot.children) {
                        val message = postSnapshot.getValue(message::class.java)
                        messageList.add(message!!)
                    }
                    messageadapter.notifyDataSetChanged()
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })
       // messagerecyclerview.layoutManager=LinearLayoutManager(this)
       // messagerecyclerview.adapter =messageadapter

        /*bauth.child("chats").child(senderroom!!).child("message")
            .addValueEventListener(object :ValueEventListener{


                @SuppressLint("NotifyDataSetChanged")
                override fun onDataChange(snapshot: DataSnapshot) {

                    messageList.clear()
                    for (postSnapshot in snapshot.children) {
                        val message = postSnapshot.getValue(message::class.java)
                        messageList.add(message!!)
                    }
                    messageadapter.notifyDataSetChanged()
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })
*/
        sedbuttton.setOnClickListener {
            val mes=messagebox.text.toString()
            val messageObject=message(mes,senderuid)
            bauth.child("chats").child(senderroom!!).child("messages").push()
                .setValue(messageObject).addOnSuccessListener {
                    bauth.child("chats").child(reseiverroom!!).child("messages").push()
                        .setValue(messageObject)
                }
             messagebox.setText(" ")
        }
    }
}