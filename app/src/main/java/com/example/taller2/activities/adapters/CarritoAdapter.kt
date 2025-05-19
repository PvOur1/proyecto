package com.example.taller2.activities.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.taller2.R
import com.example.taller2.activities.models.Moto

class CarritoAdapter : RecyclerView.Adapter<CarritoAdapter.MotoViewHolder>() {

    private var motos = listOf<Moto>()

    fun submitList(lista: List<Moto>) {
        motos = lista
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MotoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_moto_carrito, parent, false)
        return MotoViewHolder(view)
    }

    override fun onBindViewHolder(holder: MotoViewHolder, position: Int) {
        holder.bind(motos[position])
    }

    override fun getItemCount() = motos.size

    inner class MotoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvModelo: TextView = itemView.findViewById(R.id.tvModelo)
        private val tvTipoMotor: TextView = itemView.findViewById(R.id.tvTipoMotor)
        private val tvAnioPrecio: TextView = itemView.findViewById(R.id.tvAnioPrecio)
        private val imgMoto: ImageView = itemView.findViewById(R.id.imgMoto)

        fun bind(moto: Moto) {
            tvModelo.text = moto.modelo
            tvTipoMotor.text = moto.tipo_motor
            tvAnioPrecio.text = itemView.context.getString(R.string.anio_precio, moto.año, moto.precio)

            // Aquí cargamos la imagen con Glide, con placeholder mientras carga
            Glide.with(itemView.context)
                .load(moto.images)
                .centerCrop()
                .into(imgMoto)
        }
    }
}
