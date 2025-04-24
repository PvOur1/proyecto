package com.example.taller2.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.taller2.R

class PerfilFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_perfil, container, false)


        val editProfileButton = view.findViewById<Button>(R.id.edit_profile_button)


        editProfileButton.setOnClickListener {

            findNavController().navigate(R.id.action_perfilFragment_to_editarPerfilFragment)
        }

        return view
    }
}
