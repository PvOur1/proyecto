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
        return inflater.inflate(R.layout.fragment_productos, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val modelo = arguments?.getString("modelo")
        val descripcion = arguments?.getString("descripcion")
        val imagen = arguments?.getString("imagen")
        val precio = arguments?.getString("precio")
        val kilometraje = arguments?.getString("kilometraje")

        view.findViewById<TextView>(R.id.textModelo).text = modelo
        view.findViewById<TextView>(R.id.textDescripcion).text = descripcion
        view.findViewById<TextView>(R.id.textPrecio).text = "Precio: $precio"
        view.findViewById<TextView>(R.id.textKilometraje).text = "Kilometraje: $kilometraje"

        Glide.with(requireContext())
            .load(imagen)
            .placeholder(R.drawable.placeholder)
            .into(view.findViewById(R.id.imageProducto))
    }
}
