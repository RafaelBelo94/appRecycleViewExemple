package com.example.applayoutandtoast

import android.content.Intent
import android.os.Bundle
import android.widget.RadioButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.applayoutandtoast.databinding.ActivityMainHomeBinding

class MainHome : AppCompatActivity() {
    private lateinit var binding: ActivityMainHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonCadastrarAposta.setOnClickListener{
            val database = DatabaseHelper(this)
            val titulo = binding.inputTitulo.text.toString()
            val descricao = binding.inputDecricao.text.toString()
            val valor = binding.inputValor.text.toString()
            var formaPagamento: String = ""

            binding.radioGroup.setOnCheckedChangeListener{radioGroup, checkedId ->
                val radioButton: RadioButton = findViewById(checkedId)
                formaPagamento = radioButton.text.toString()
            }
            database.cadastrarAposta(titulo, descricao, valor.toDouble(), formaPagamento)
        }

        binding.buttonListarAposta.setOnClickListener {
            startActivity(Intent(this, ListarApostasActivity::class.java))
        }
    }
}