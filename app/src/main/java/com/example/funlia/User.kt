package com.example.funlia

class User {

    var name : String?=null
    var email : String?=null
    var uid : String?=null
    var phone : String?=null
    var address : String?=null
    var birth : String?=null
    var password1 : String?=null


    constructor(){}

    constructor(name:String?,email:String?,uid:String?,phone:String?,address:String?,birth:String?,password1:String?){
        this.name=name
        this.email=email
        this.uid=uid
        this.phone=phone
        this.address=address
        this.birth=birth
        this.password1=password1


    }


}