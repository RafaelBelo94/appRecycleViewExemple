package com.example.applayoutandtoast

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, "mydb.db", null, 1) {

    val sql = arrayOf(
        "CREATE TABLE aposta(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "titulo TEXT NOT NULL," +
                "descricao TEXT NOT NULL," +
                "valor REAL NOT NULL," +
                "forma_pagamento TEXT NOT NULL);",

        "CREATE TABLE usuario(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nome TEXT NOT NULL," +
                "email TEXT NOT NULL UNIQUE," +
                "senha TEXT NOT NULL);",

        "INSERT INTO usuario(nome,email,senha) VALUES('admin','admin','admin')"
    )


    override fun onCreate(db: SQLiteDatabase) {
        sql.forEach {
            db.execSQL(it)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS aposta")
        db.execSQL("DROP TABLE IF EXISTS usuario")
        onCreate(db)
    }

    fun validarLogin(email: String, senha: String): Boolean {
        val db = this.readableDatabase
        val cursor = db.rawQuery(
            "SELECT * FROM usuario WHERE email = ? AND senha = ?",
            arrayOf(email, senha)
        )
        val valido = cursor.moveToNext()
        return valido
    }

    fun validarEmail(email: String): Boolean {
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM usuario WHERE email = ?", arrayOf(email))
        val valido = cursor.moveToNext()
        return valido
    }

    fun cadastrarUsuario(nome: String, email: String, senha: String): Long {
        val usuario = ContentValues().apply {
            put("nome", nome)
            put("email", email)
            put("senha", senha)
        }
        return writableDatabase.insert("usuario", null, usuario)
    }

    fun cadastrarAposta(
        titulo: String,
        descricao: String,
        valor: Double,
        formaPagamento: String
    ):Long {
        val aposta = ContentValues().apply {
            put("titulo", titulo)
            put("descricao", descricao)
            put("valor", valor)
            put("forma_pagamento", formaPagamento)
        }
        return writableDatabase.insert("aposta", null, aposta)
    }

    fun listaApostas(): List<Aposta> {
        val apostas = mutableListOf<Aposta>()
        val cursor = readableDatabase.query(true, "aposta", null, null, null, null, null, null, null)

        cursor.use {
            while (it.moveToNext()) {
                var idIndex = it.getColumnIndex("id")
                var tituloIndex = it.getColumnIndex("titulo")
                var descricaoIndex = it.getColumnIndex("descricao")
                var valorIndex = it.getColumnIndex("valor")
                var formaPagamentoIndex = it.getColumnIndex("forma_pagamento")

                apostas.add(
                 Aposta(
                     it.getInt(idIndex),
                     it.getString(tituloIndex),
                     it.getString(descricaoIndex),
                     it.getDouble(valorIndex),
                     it.getString(formaPagamentoIndex)
                 )
                )

            }
        }
        return apostas
    }

}