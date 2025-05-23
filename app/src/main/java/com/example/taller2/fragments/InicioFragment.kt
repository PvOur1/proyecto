package com.example.taller2.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.VideoView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.taller2.R
import com.example.taller2.activities.models.Moto
import com.example.taller2.activities.network.RetrofitClient
import com.example.taller2.adapters.MotoAdapter2
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InicioFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MotoAdapter2

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_inicio, container, false)

        recyclerView = view.findViewById(R.id.recyclerMotos)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        adapter = MotoAdapter2(requireContext()) { moto ->
            Toast.makeText(requireContext(), "Seleccionaste: ${moto.modelo}", Toast.LENGTH_SHORT).show()
        }
        recyclerView.adapter = adapter

        cargarMotosDesdeAPI()

        val videoView = view.findViewById<VideoView>(R.id.videoView)
        val videoUri = Uri.parse("android.resource://${requireContext().packageName}/${R.raw.publicidad}")
        videoView.setVideoURI(videoUri)
        videoView.start()

        return view
    }

    private fun cargarMotosDesdeAPI() {
        val service = RetrofitClient.instance
        val sharedPreferences = requireContext().getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
        val tokenNullable = sharedPreferences.getString("token", null)
        val idUsuario = sharedPreferences.getInt("idUsuario", -1)

        if (tokenNullable.isNullOrEmpty() || idUsuario == -1) {
            Toast.makeText(requireContext(), "Token o ID de usuario inválido", Toast.LENGTH_SHORT).show()
            return
        }

        val token = "Bearer $tokenNullable"
        service.getMotosUsuario(token, idUsuario).enqueue(object : Callback<List<Moto>> {
            override fun onResponse(call: Call<List<Moto>>, response: Response<List<Moto>>) {
                if (response.isSuccessful && response.body() != null) {
                    adapter.setMotos(response.body()!!)
                } else {
                    Toast.makeText(requireContext(), "No se encontraron motos del usuario", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<Moto>>, t: Throwable) {
                Toast.makeText(requireContext(), "Error al cargar motos: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
