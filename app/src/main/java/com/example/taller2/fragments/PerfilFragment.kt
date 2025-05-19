package com.example.taller2.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.taller2.R
import com.example.taller2.activities.models.Moto
import com.example.taller2.activities.network.RetrofitClient
import com.example.taller2.adapters.MotoAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PerfilFragment : Fragment() {

    private lateinit var motoAdapter: MotoAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_perfil, container, false)

        Log.d("ProfileFragment", "onCreateView ejecutado")

        val nombresTextView = view.findViewById<TextView>(R.id.nombreTextView)
        val correoTextView = view.findViewById<TextView>(R.id.correoTextView)
        val telefonoTextView = view.findViewById<TextView>(R.id.telefonoTextView)
        val cantidadpubli = view.findViewById<TextView>(R.id.cantifafTextView)

        // Configurar RecyclerView
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewMotos)
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 3) // 3 columnas

        // Adapter con función de clic
        motoAdapter = MotoAdapter(requireContext()) { moto ->
            val fragment = ProductosFragment()
            val args = Bundle().apply {
                putString("modelo", moto.modelo)
                putString("descripcion", moto.descripcion)
                putString("imagen", moto.images)
                putString("precio", moto.precio)
                putString("kilometraje", moto.kilometraje)
            }
            fragment.arguments = args

            parentFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment, fragment)
                .addToBackStack(null)
                .commit()
        }

        recyclerView.adapter = motoAdapter

        // Obtener datos del usuario desde SharedPreferences
        val sharedPreferences = requireContext().getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
        val nombres = sharedPreferences.getString("nombre", "juan")
        val apellidos = sharedPreferences.getString("apellido", "hernandez")
        val correo = sharedPreferences.getString("correo", "No registrado")
        val telefono = sharedPreferences.getString("telefono", "3105853456")
        val cantidadMotos = sharedPreferences.getInt("cantidadMotos", 0)
        val userId = sharedPreferences.getInt("idUsuario", 0)
        val token = sharedPreferences.getString("token", "")

        nombresTextView.text = "$nombres $apellidos"
        correoTextView.text = correo
        telefonoTextView.text = telefono
        cantidadpubli.text = cantidadMotos.toString()

        if (token != null) {
            val call = RetrofitClient.instance.getMotosUsuario("Bearer $token", userId)
            call.enqueue(object : Callback<List<Moto>> {
                override fun onResponse(call: Call<List<Moto>>, response: Response<List<Moto>>) {
                    if (response.isSuccessful) {
                        val motos = response.body()
                        if (motos != null) {
                            motoAdapter.setMotos(motos)
                        }
                    } else {
                        Log.e("API", "Error: ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<List<Moto>>, t: Throwable) {
                    Log.e("API", "Fallo: ${t.message}")
                }
            })
        } else {
            Log.e("TOKEN", "Token no encontrado en SharedPreferences")
        }

        return view
    }
}
