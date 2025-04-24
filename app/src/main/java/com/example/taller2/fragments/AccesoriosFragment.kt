package com.example.taller2.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.taller2.R
import androidx.navigation.fragment.findNavController

class AccesoriosFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_cat_accesorios, container, false)

        val button_volver = view.findViewById<Button>(R.id.button_volver)

        button_volver.setOnClickListener {
            findNavController().navigate(R.id.action_accesoriosFragment_to_CategoriasFragment)
        }
        return view
    }

}