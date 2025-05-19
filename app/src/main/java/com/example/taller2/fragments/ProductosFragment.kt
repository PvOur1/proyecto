package com.example.taller2.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.taller2.R

class ProductosFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_productos, container, false)

        val modeloTextView = view.findViewById<TextView>(R.id.tvModelo)
        val descripcionTextView = view.findViewById<TextView>(R.id.tvDescripcion)
        val imageView = view.findViewById<ImageView>(R.id.ivMoto)
        val precioTextView = view.findViewById<TextView>(R.id.tvPrecio)
        val kilometrajeTextView = view.findViewById<TextView>(R.id.tvKilometraje)
        val colorTextView = view.findViewById<TextView>(R.id.tvColor)
        val anioTextView = view.findViewById<TextView>(R.id.tvAnio)
        val combustibleTextView = view.findViewById<TextView>(R.id.tvCombustible)
        val transmisionTextView = view.findViewById<TextView>(R.id.tvTransmision)
        val tipoMotorTextView = view.findViewById<TextView>(R.id.tvTipoMotor)

        val args = arguments

        modeloTextView.text = args?.getString("modelo")
        descripcionTextView.text = args?.getString("descripcion")
        precioTextView.text = "$${args?.getString("precio")}"
        kilometrajeTextView.text = "Kilometraje: ${args?.getString("kilometraje")}"
        colorTextView.text = "Color: ${args?.getString("color")}"
        anioTextView.text = "Año: ${args?.getString("anio")}"
        combustibleTextView.text = "Combustible: ${args?.getString("combustible")}"
        transmisionTextView.text = "Transmisión: ${args?.getString("transmision")}"
        tipoMotorTextView.text = "Tipo de motor: ${args?.getString("tipo_motor")}"

        val imagen = args?.getString("imagen")
        Glide.with(requireContext())
            .load(imagen)
            .into(imageView)

        return view
    }
}
