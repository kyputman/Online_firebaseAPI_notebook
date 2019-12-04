package com.example.home_pc.samsung_proj


class onFile{
    lateinit var name:String
    lateinit var owner:String
    lateinit var content:String
    constructor()
    constructor(name: String, owner: String, content: String) {
        this.name = name
        this.owner = owner
        this.content = content
    }

}
