package com.example.applayoutandtoast

import javax.security.auth.callback.PasswordCallback

class User {
    var nome: String = ""
    var email: String = ""
    var password: String = ""

    constructor()

    constructor(_nome:String, _email: String, _password: String) {
        this.nome = _nome
        this.email = _email
        this.password = _password
    }

    fun get_email(): String{
        return email
    }
    fun get_password(): String{
        return password
    }

}