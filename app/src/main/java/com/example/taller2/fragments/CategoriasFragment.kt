package com.example.taller2.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.taller2.R
import androidx.navigation.fragment.findNavController

class CategoriasFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_categorias, container, false)

        val More_button_Electronica = view.findViewById<Button>(R.id.More_button_Electronica)
        val More_button_Ropa = view.findViewById<Button>(R.id.More_button_Ropa)
        val More_button_Hogar = view.findViewById<Button>(R.id.More_button_Hogar)
        val More_button_Deportes = view.findViewById<Button>(R.id.More_button_Deportes)
        val More_button_Accesorios = view.findViewById<Button>(R.id.More_button_Accesorios)

        More_button_Electronica.setOnClickListener {
            findNavController().navigate(R.id.action_CategoriasFragment_to_electronicaFragment)
        }
        More_button_Ropa.setOnClickListener {
            findNavController().navigate(R.id.action_CategoriasFragment_to_ropaFragment)
        }
        More_button_Hogar.setOnClickListener {
            findNavController().navigate(R.id.action_CategoriasFragment_to_hogarFragment)
        }
        More_button_Deportes.setOnClickListener {
            findNavController().navigate(R.id.action_CategoriasFragment_to_deporteFragment)
        }
        More_button_Accesorios.setOnClickListener {
            findNavController().navigate(R.id.action_CategoriasFragment_to_accesoriosFragment)
        }
        return view
    }
}