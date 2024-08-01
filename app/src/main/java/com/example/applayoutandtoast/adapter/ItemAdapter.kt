package com.example.applayoutandtoast.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.applayoutandtoast.Aposta
import com.example.applayoutandtoast.R

class ItemAdapter(val items: List<Aposta>): RecyclerView.Adapter<ItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_listar_apostas, parent, false)

        return ItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.tituloText.text = items[position].titulo
        holder.descricaoText.text = items[position].descricao
        holder.valorText.text = "R$ " + items[position].valor.toString()
    }

}