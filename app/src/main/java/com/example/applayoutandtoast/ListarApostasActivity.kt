package com.example.applayoutandtoast

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.applayoutandtoast.adapter.ItemAdapter
import com.example.applayoutandtoast.databinding.ActivityListarApostasBinding

class ListarApostasActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListarApostasBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityListarApostasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val database = DatabaseHelper(this)
        val recyclerView: RecyclerView = binding.recycleList
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = ItemAdapter(database.listaApostas())
    }
}