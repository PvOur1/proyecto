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

        val More_button_kawasaki = view.findViewById<Button>(R.id.More_button_Kawasaki)
        val More_button_ktm = view.findViewById<Button>(R.id.More_button_KTM)
        val More_button_yamaha = view.findViewById<Button>(R.id.More_button_Yamaha)
        val More_button_ducati = view.findViewById<Button>(R.id.More_button_Ducati)
        val More_button_suzuki = view.findViewById<Button>(R.id.More_button_Suzuki)

        More_button_kawasaki.setOnClickListener {
            findNavController().navigate(R.id.action_CategoriasFragment_to_KawasakiFragment)
        }
        More_button_ktm.setOnClickListener {
            findNavController().navigate(R.id.action_CategoriasFragment_to_KtmFragment)
        }
        More_button_yamaha.setOnClickListener {
            findNavController().navigate(R.id.action_CategoriasFragment_to_YamahaFragment)
        }
        More_button_ducati.setOnClickListener {
            findNavController().navigate(R.id.action_CategoriasFragment_to_DucatiFragment)
        }
        More_button_suzuki.setOnClickListener {
            findNavController().navigate(R.id.action_CategoriasFragment_to_SuzukiFragment)
        }
        return view
    }
}