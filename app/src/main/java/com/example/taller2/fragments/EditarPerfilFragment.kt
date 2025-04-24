package com.example.taller2.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.taller2.R
import com.example.taller2.R.id.go_home_button

class EditarPerfilFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_editar_perfil, container, false)


        val save_perfile_button = view.findViewById<Button>(R.id.save_profile_button)
        val go_home_button = view.findViewById<Button>(R.id.go_home_button)


        save_perfile_button.setOnClickListener {

            findNavController().navigate(R.id.action_editarPerfilFragment_to_perfilFragment)
        }
        go_home_button.setOnClickListener {
            findNavController().navigate(R.id.action_editarPerfilFragment_to_inicioFragment)
        }
        return view
    }
}