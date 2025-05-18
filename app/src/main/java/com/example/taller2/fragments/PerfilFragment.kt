package com.example.taller2.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.taller2.R
import com.example.taller2.activities.LoginActivity

class PerfilFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_perfil, container, false)

        Log.d("ProfileFragment", "onCreateView ejecutado")

        val nombresTextView = view.findViewById<TextView>(R.id.nombreTextView)
        //val apellidosTextView = view.findViewById<TextView>(R.id.apellidoTextView)
        val correoTextView = view.findViewById<TextView>(R.id.correoTextView)
        val telefonoTextView = view.findViewById<TextView>(R.id.telefonoTextView)
        //val edadTextView =  view.findViewById<TextView>(R.id.edadtxvw)
        val cantidadpubli = view.findViewById<TextView>(R.id.cantifafTextView)




        val sharedPreferences = requireContext().getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
        val nombres = sharedPreferences.getString("nombre", "juan")
        val apellidos = sharedPreferences.getString("apellido", "hernandez")
        val correo = sharedPreferences.getString("correo", "No registrado")
        val telefono = sharedPreferences.getString("telefono", "3105853456")
        //val edad = sharedPreferences.getString("edad","")
        val cantidadMotos = sharedPreferences.getInt("cantidadMotos", 0)

        nombresTextView.text = nombres + " " +   apellidos

        correoTextView.text = correo
        telefonoTextView.text = telefono
        //edadTextView.text = edad
        cantidadpubli.text = cantidadMotos.toString()

        //val btnEditar = view.findViewById<Button>(R.id.btnEditar)
        //val btnCentral = view.findViewById<Button>(R.id.actividad_central)

//        btnEditar.setOnClickListener {
//            Log.d("ProfileFragment", "Botón Editar presionado")
//        }

        return view
    }
}
