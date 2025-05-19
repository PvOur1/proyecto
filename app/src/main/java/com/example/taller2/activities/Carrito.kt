package com.example.taller2.activities

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.taller2.R
import com.example.taller2.activities.adapters.CarritoAdapter
import com.example.taller2.activities.models.CarritoModel
import com.example.taller2.activities.models.LoginResponse
import com.example.taller2.activities.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Carrito : Fragment(R.layout.fragment_carrito) {

    private lateinit var carritoAdapter: CarritoAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d("Carrito", "Fragment onCreateView ejecutado")

        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewCarrito)
        carritoAdapter = CarritoAdapter()
        recyclerView.adapter = carritoAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val sharedPreferences = requireContext().getSharedPreferences("UserPrefs", 0)
        val userId = sharedPreferences.getInt("idUsuario", -1)
        val token = sharedPreferences.getString("token", "")

        Log.d("Carrito", "userId: $userId, token: $token")

        if (userId != -1 && !token.isNullOrEmpty()) {
            RetrofitClient.instance.getCarritoUsuario(token, userId)
                .enqueue(object : Callback<CarritoModel> {
                    override fun onResponse(call: Call<CarritoModel>, response: Response<CarritoModel>) {
                        if (response.isSuccessful && response.body() != null) {
                            val carrito = response.body()!!
                            Log.d("Carrito", "Carrito recibido: $carrito")
                            // Actualiza el adapter con la lista de motos
                            carritoAdapter.submitList(carrito.motos)
                        } else {
                            Toast.makeText(requireContext(), "Error al obtener carrito", Toast.LENGTH_SHORT).show()
                        }
                    }
                    override fun onFailure(call: Call<CarritoModel>, t: Throwable) {
                        Toast.makeText(requireContext(), "Fallo de red: ${t.message}", Toast.LENGTH_SHORT).show()
                    }
                })
        } else {
            Toast.makeText(requireContext(), "ID de usuario o token no encontrado", Toast.LENGTH_SHORT).show()
        }
    }
}
