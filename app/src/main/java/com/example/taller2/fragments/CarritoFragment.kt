package com.example.taller2.fragments

import android.content.Context
import android.os.Bundle
import android.telecom.Call
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.taller2.R
import com.example.taller2.activities.adapters.CarritoAdapter
import com.example.taller2.activities.models.CarritoItem
import com.example.taller2.activities.network.RetrofitClient
import javax.security.auth.callback.Callback

class CarritoFragment : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var carritoAdapter: CarritoAdapter
    private var listaCarrito: MutableList<CarritoItem> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_carrito)

        recyclerView = findViewById(R.id.recyclerViewCarrito)
        recyclerView.layoutManager = LinearLayoutManager(this)
        carritoAdapter = CarritoAdapter(listaCarrito)
        recyclerView.adapter = carritoAdapter


    }
}


