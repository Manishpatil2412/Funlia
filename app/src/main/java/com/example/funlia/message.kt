package com.example.funlia

class message {
    var message: String?=null
    var userid:String?=null

    constructor(){}

    constructor(message:String, userid: String?){
        this.message=message
        this.userid=userid
    }
}