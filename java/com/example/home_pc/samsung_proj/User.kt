package com.example.home_pc.samsung_proj

import java.util.*


class User{
    lateinit var login:String
    lateinit var password:String
    constructor(){}
    constructor(login: String,password:String){
        this.login=login
        this.password=password
    }
    fun PasEquals(s:String):Boolean{
        return (this.password==s)
    }
    fun LogEquals(s:String):Boolean{
        return (this.login==s)
    }

}


