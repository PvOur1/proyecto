package com.example.taller2.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.taller2.R
import com.example.taller2.activities.models.Moto

class MotoAdapter(
    private val context: Context,
    private val onMotoClick: (Moto) -> Unit // Función que se ejecuta al hacer clic
) : RecyclerView.Adapter<MotoAdapter.MotoViewHolder>() {

    private val motos = mutableListOf<Moto>()

    fun setMotos(lista: List<Moto>) {
        motos.clear()
        motos.addAll(lista)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MotoViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_moto, parent, false)
        return MotoViewHolder(view)
    }

    override fun getItemCount(): Int = motos.size

    override fun onBindViewHolder(holder: MotoViewHolder, position: Int) {
        val moto = motos[position]
        Log.d("MotoAdapter", "Cargando imagen: ${moto.images}")

        Glide.with(holder.itemView.context)
            .load(moto.images)
            .placeholder(R.drawable.placeholder)
            .into(holder.imagen)

        // Clic sobre el item
        holder.itemView.setOnClickListener {
            onMotoClick(moto) // Llama la función que recibe la moto clickeada
        }
    }

    class MotoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imagen: ImageView = view.findViewById(R.id.motoImageView)
    }
}
