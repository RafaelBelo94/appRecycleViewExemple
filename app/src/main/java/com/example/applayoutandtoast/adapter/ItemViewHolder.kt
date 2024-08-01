package com.example.applayoutandtoast.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.applayoutandtoast.R

class ItemViewHolder(view : View): RecyclerView.ViewHolder(view) {
    val tituloText: TextView = view.findViewById(R.id.tituloAposta)
    val descricaoText : TextView = view.findViewById(R.id.descricaoAposta)
    val valorText: TextView = view.findViewById(R.id.valorAposta)
}