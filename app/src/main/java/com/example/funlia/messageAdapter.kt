package com.example.funlia

import android.content.Context
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth



class messageadapter(val context: Context, val messageList: ArrayList<message>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val ITEM_RECEIVE=1;
    val ITEM_SEND=2;
    override fun getItemCount(): Int {
        return messageList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentmessage=messageList[position]

        if (holder.javaClass == sendviewholder::class.java) {
            val viewHolder = holder as sendviewholder
            holder.sentmessage.text=currentmessage.message
        } else {

            val viewHolder = holder as receiveholder
            holder.receivemessage.text=currentmessage.message

        }

    }

    override fun getItemViewType(position: Int): Int {
        val currentmessage = messageList[position]
        if(FirebaseAuth.getInstance().currentUser?.uid.equals(currentmessage.userid)){
            return ITEM_SEND
        }else{
            return ITEM_RECEIVE
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if(viewType == 1){
            val view : View = LayoutInflater.from(context).inflate(R.layout.receive,parent,false)
            return receiveholder(view)

        }else{
            val view : View = LayoutInflater.from(context).inflate(R.layout.send,parent,false)
            return sendviewholder(view)

        }
    }

    class sendviewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val sentmessage=itemView.findViewById<TextView>(R.id.txtsend)

    }

    class receiveholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val receivemessage=itemView.findViewById<TextView>(R.id.txtrec)

    }

}

