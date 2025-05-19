package com.example.taller2.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.taller2.R
import com.example.taller2.activities.models.Moto

class MotoAdapter2(
    private val context: Context,
    private val onMotoClick: (Moto) -> Unit
) : RecyclerView.Adapter<MotoAdapter2.MotoViewHolder2>() {

    private val motos = mutableListOf<Moto>()

    fun setMotos(lista: List<Moto>) {
        motos.clear()
        motos.addAll(lista)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MotoViewHolder2 {
        val view = LayoutInflater.from(context).inflate(R.layout.item_moto2, parent, false)
        return MotoViewHolder2(view)
    }

    override fun getItemCount(): Int = motos.size

    override fun onBindViewHolder(holder: MotoViewHolder2, position: Int) {
        val moto = motos[position]
        Log.d("MotoAdapter2", "Cargando imagen: ${moto.images}")

        Glide.with(holder.itemView.context)
            .load(moto.images)
            .placeholder(R.drawable.placeholder)
            .into(holder.imagen)

        // Mostrar siempre el modelo y precio
        holder.modelo.text = moto.modelo
        holder.precio.text = "$${moto.precio}"

        // Clic sobre el item
        holder.itemView.setOnClickListener {
            onMotoClick(moto)
        }
    }

    class MotoViewHolder2(view: View) : RecyclerView.ViewHolder(view) {
        val imagen: ImageView = view.findViewById(R.id.imageMoto)
        val modelo: TextView = view.findViewById(R.id.modeloMoto)
        val precio: TextView = view.findViewById(R.id.precioMoto)
    }

}
